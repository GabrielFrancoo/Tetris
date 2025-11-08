package main.Java.tetris.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Java.tetris.domain.Jogador;
import main.Java.tetris.domain.Partida;

public class PartidaDAO {

    public void salvar(Partida partida) {
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
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public Partida carregarUltima(Jogador jogador) {
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
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
        return null;
    }
}
