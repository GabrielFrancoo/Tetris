package tetris.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import tetris.domain.Partida;

public class ReplayManager {
    private static final String REPLAY_FILE = "replay_tetris.dat";

    public static void salvarReplay(List<Partida> historico) {
        if (historico == null || historico.isEmpty()) {
            System.out.println("[REPLAY] Nenhum histórico para salvar");
            return;
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPLAY_FILE))) {
            oos.writeObject(historico);
            System.out.println("[REPLAY] Salvo: " + historico.size() + " frames");
        } catch (IOException e) {
            System.err.println("[REPLAY] Erro ao salvar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Partida> carregarReplay() {
        File file = new File(REPLAY_FILE);
        if (!file.exists()) {
            System.out.println("[REPLAY] Nenhum replay encontrado");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Partida>) ois.readObject();
        } catch (Exception e) {
            System.err.println("[REPLAY] Erro: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void reproduzir(List<Partida> historico, int delayMs) {
        if (historico == null || historico.isEmpty()) {
            System.out.println("[REPLAY] Nenhum histórico para reproduzir");
            return;
        }
        
        for (int i = 0; i < historico.size(); i++) {
            System.out.println("Frame " + i + ": Pontos = " + historico.get(i).getSistemaPontuacao().getPontos());
            try { 
                Thread.sleep(delayMs); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[REPLAY] Reprodução interrompida");
                return;
            }
        }
        System.out.println("[REPLAY] Reprodução concluída");
    }
}

