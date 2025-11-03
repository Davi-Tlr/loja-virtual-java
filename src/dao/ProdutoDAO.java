package dao;

import database.ConexaoBD;
import model.product.Produto;
import model.product.ProdutoDigital;
import model.product.ProdutoFisico;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto p) {
        String sql = "INSERT INTO produto (nome, preco, tipo, peso, tamanho_arquivo_mb) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, p.getNome());
            pstmt.setDouble(2, p.getPreco());
            if (p instanceof ProdutoFisico) {
                pstmt.setString(3, "FISICO");
                pstmt.setDouble(4, ((ProdutoFisico) p).getPeso());
                pstmt.setNull(5, Types.REAL);
            } else if (p instanceof ProdutoDigital pd) {
                pstmt.setString(3, "DIGITAL");
                pstmt.setNull(4, Types.REAL);
                pstmt.setDouble(5, pd.getTamanhoArquivoMB());
            } else {
                pstmt.setNull(3, Types.VARCHAR);
                pstmt.setNull(4, Types.REAL);
                pstmt.setNull(5, Types.REAL);
            }

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
            System.out.println("Produto inserido com sucesso! ID: " +p.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Produto p;

                if ("FISICO".equals(tipo)) {
                    p = new ProdutoFisico(rs.getInt("id"),
                                    rs.getString("nome"),
                                    rs.getDouble("preco"),
                                    rs.getDouble("peso"));
                } else if ("DIGITAL".equals(tipo)) {
                    p = new ProdutoDigital(rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getDouble("tamanho_arquivo_mb"));
                } else {
                    p = new Produto(rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco"));
                }
                produtos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    return produtos;
    }

    public Produto buscarPorId(int id) {
        Produto p = null;
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setPreco(rs.getDouble("preco"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
        return p;
    }
}
