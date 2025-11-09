package tetris.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tetris.domain.Jogador;
import tetris.domain.Partida;

public class PartidaDAO {

    public void criarTabelaSeNaoExistir() {
        String sql = """
            IF NOT EXISTS (
                SELECT * FROM sysobjects WHERE name='Partida' and xtype='U'
            )
            CREATE TABLE Partida (
                id INT IDENTITY(1,1) PRIMARY KEY,
                jogador_id UNIQUEIDENTIFIER NOT NULL,
                dados VARBINARY(MAX) NOT NULL,
                data_criacao DATETIME NOT NULL DEFAULT GETDATE(),
                FOREIGN KEY (jogador_id) REFERENCES Jogador(id)
            );
        """;
        try (Connection conn = ConexaoSQL.getConexao(); 
             Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("[DAO] Tabela Partida verificada/criada");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela Partida: " + e.getMessage());
        }
    }

    public void salvar(Partida partida) {
        if (partida == null) {
            throw new IllegalArgumentException("Partida não pode ser nula");
        }
        
        String sql = "INSERT INTO Partida (jogador_id, dados, data_criacao) VALUES (?, ?, GETDATE())";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(partida);
            }
            ps.setObject(1, partida.getJogador().getId());
            ps.setBytes(2, baos.toByteArray());
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            System.err.println("Erro ao salvar partida: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar partida", e);
        }
    }

    public Partida carregarUltima(Jogador jogador) {
        if (jogador == null || jogador.getId() == null) {
            return null;
        }
        
        String sql = "SELECT TOP 1 dados FROM Partida WHERE jogador_id = ? ORDER BY data_criacao DESC";
        try (Connection conn = ConexaoSQL.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, jogador.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] blob = rs.getBytes("dados");
                    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(blob))) {
                        return (Partida) ois.readObject();
                    }
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar partida: " + e.getMessage());
        }
        return null;
    }
}

