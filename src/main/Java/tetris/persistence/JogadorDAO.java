package main.Java.tetris.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Java.tetris.domain.Jogador;

public class JogadorDAO {

    public void criarTabelaSeNaoExistir() {
        String sql = """
            IF NOT EXISTS (
                SELECT * FROM sysobjects WHERE name='Jogador' and xtype='U'
            )
            CREATE TABLE Jogador (
                id UNIQUEIDENTIFIER PRIMARY KEY,
                nome NVARCHAR(100) NOT NULL
            );
        """;
        try (Connection conn = ConexaoSQL.getConexao(); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void inserir(Jogador jogador) {
        String sql = "INSERT INTO Jogador (id, nome) VALUES (?, ?)";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, jogador.getId());
            ps.setString(2, jogador.getNome());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Jogador> listarTodos() {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT id, nome FROM Jogador";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                java.util.UUID id = (java.util.UUID) rs.getObject("id");
                String nome = rs.getString("nome");
                Jogador j = new Jogador(nome);
                // reflexão para ajustar id não aplicada aqui; simplificação
                jogadores.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return jogadores;
    }
}
