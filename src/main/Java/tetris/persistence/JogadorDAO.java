package tetris.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tetris.domain.Jogador;

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
        try (Connection conn = ConexaoSQL.getConexao(); 
             Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("[DAO] Tabela Jogador verificada/criada");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void inserir(Jogador jogador) {
        if (jogador == null) {
            throw new IllegalArgumentException("Jogador não pode ser nulo");
        }
        
        String sql = "INSERT INTO Jogador (id, nome) VALUES (?, ?)";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, jogador.getId());
            ps.setString(2, jogador.getNome());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
            throw new RuntimeException("Falha ao inserir jogador", e);
        }
    }

    public List<Jogador> listarTodos() {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT id, nome FROM Jogador";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String nome = rs.getString("nome");
                // Usa o construtor que aceita UUID para manter o ID do banco
                Jogador j = new Jogador(id, nome);
                jogadores.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return jogadores;
    }
    
    public Jogador buscarPorId(UUID id) {
        if (id == null) {
            return null;
        }
        
        String sql = "SELECT id, nome FROM Jogador WHERE id = ?";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UUID jogadorId = (UUID) rs.getObject("id");
                    String nome = rs.getString("nome");
                    return new Jogador(jogadorId, nome);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }
}

