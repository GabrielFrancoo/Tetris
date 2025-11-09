package tetris.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tetris.domain.Partida;

public class SaveManager {
    private static final String SAVE_DIR = "saves";
    private static final String SAVE_EXT = ".sav";

    public void salvarPartida(Partida partida) throws IOException {
        if (partida == null) {
            throw new IllegalArgumentException("Partida não pode ser nula");
        }
        
        File saveDir = new File(SAVE_DIR);
        if (!saveDir.exists()) {
            boolean criado = saveDir.mkdir();
            if (!criado) {
                throw new IOException("Não foi possível criar o diretório de saves");
            }
        }

        String nomeJogador = partida.getJogador().getNome();
        if (nomeJogador == null || nomeJogador.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do jogador inválido");
        }
        
        // Sanitiza o nome do arquivo removendo caracteres inválidos
        String nomeArquivo = nomeJogador.replaceAll("[^a-zA-Z0-9\\s_-]", "");
        String fileName = SAVE_DIR + File.separator + nomeArquivo + SAVE_EXT;

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            oos.writeObject(partida);
        }
    }

    public Partida carregarPartida(String nomeJogador) throws IOException, ClassNotFoundException {
        if (nomeJogador == null || nomeJogador.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do jogador inválido");
        }
        
        String nomeArquivo = nomeJogador.replaceAll("[^a-zA-Z0-9\\s_-]", "");
        String fileName = SAVE_DIR + File.separator + nomeArquivo + SAVE_EXT;
        File saveFile = new File(fileName);
        
        if (!saveFile.exists()) {
            throw new IOException("Arquivo de save não encontrado para " + nomeJogador);
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (Partida) ois.readObject();
        }
    }
}

