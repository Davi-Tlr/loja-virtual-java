package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    private static final String URL = "jdbc:sqlite:loja.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabelaProduto() {
        String sql = "CREATE TABLE IF NOT EXISTS produto (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "preco REAL NOT NULL," +
                "tipo TEXT," +
                "peso REAL," +
                "tamanho_arquivo_mb REAL" +
                ");";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'produto' criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar Tabela: " + e.getMessage());
        }
    }

    public static void criarTabelasPedido() {
        String sqlPedido = "CREATE TABLE IF NOT EXISTS pedido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data TEXT NOT NULL," +
                "valor_total REAL NOT NULL" +
                ");";
        String sqlPedidoProduto = "CREATE TABLE IF NOT EXISTS pedido_produto(" +
                "pedido_id INTEGER," +
                "produto_id INTEGER," +
                "PRIMARY KEY (pedido_id, produto_id)" +
                ");";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlPedido);
            stmt.execute(sqlPedidoProduto);
            System.out.println("Tabelas 'pedido' e 'pedido_produto' criadas com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas de pedido: " + e.getMessage());
        }
    }
}