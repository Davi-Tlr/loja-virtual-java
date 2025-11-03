package dao;

import database.ConexaoBD;
import model.order.ListaVaziaException;
import model.order.Pedido;
import model.product.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void inserir(Pedido p) {
        try {
            p.calcularValorTotal();
        } catch (ListaVaziaException e) {
            System.out.println("Erro ao calcular total: " + e.getMessage());
            return;
        }

        String sqlPedido = "INSERT INTO pedido (data, valor_total) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, p.getData());
            pstmt.setDouble(2, p.getValorTotal());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }

            String sqlItem = "INSERT INTO pedido_produto (pedido_id, produto_id) VALUES (?, ?)";
            try (PreparedStatement pstmtItem = conn.prepareStatement(sqlItem)) {
                for (Produto prod : p.getProdutos()) {
                    pstmtItem.setInt(1, p.getId());
                    pstmtItem.setInt(2, prod.getId());
                    pstmtItem.executeUpdate();
                }
                System.out.println("Itens do pedido inseridos com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao inserir itens do pedido: " + e.getMessage());
            }

            System.out.println("Pedido inserido com sucesso! " + p.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pedido: " + e.getMessage());
        }
    }

    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = ConexaoBD.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getInt("id"));
                ped.setData(rs.getString("data"));
                ped.setValorTotal(rs.getDouble("valor_total"));

                String sqlItens = "SELECT pr.* FROM produto pr " +
                        "INNER JOIN pedido_produto pp ON pr.id = pp.produto_id " +
                        "WHERE pp.pedido_id = ?";

                try (PreparedStatement pstmtItens = conn.prepareStatement(sqlItens)) {
                    pstmtItens.setInt(1, ped.getId());
                    try (ResultSet rsItens = pstmtItens.executeQuery()) {
                        while (rsItens.next()) {
                            String tipo = rsItens.getString("tipo");
                            Produto prod;
                            if ("FISICO".equals(tipo)) {
                                prod = new ProdutoFisico(rsItens.getInt("id"), rsItens.getString("nome"), rsItens.getDouble("preco"), rsItens.getDouble("peso"));
                            } else if ("DIGITAL".equals(tipo)) {
                                prod = new ProdutoDigital(rsItens.getInt("id"), rsItens.getString("nome"), rsItens.getDouble("preco"), rsItens.getDouble("tamanho_arquivo_mb"));
                            } else {
                                prod = new Produto(rsItens.getInt("id"), rsItens.getString("nome"), rsItens.getDouble("preco"));
                            }
                            ped.adicionarProduto(prod);
                        }

                    }
                }
                pedidos.add(ped);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
        return pedidos;
    }
}
