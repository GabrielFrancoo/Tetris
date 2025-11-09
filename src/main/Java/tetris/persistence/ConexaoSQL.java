package tetris.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL {
    // Configurações devem ser externalizadas em produção
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=TetrisDB;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "sa";
    private static final String SENHA = "senha123";
    private static Connection conexao;

    static {
        try {
            // Carrega o driver JDBC do SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do SQL Server não encontrado: " + e.getMessage());
        }
    }

    public static Connection getConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conexao;
    }

    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                conexao = null;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}

