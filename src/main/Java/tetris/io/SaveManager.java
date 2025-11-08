package main.Java.tetris.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.Java.tetris.domain.Partida;

public class SaveManager {
    private static final String SAVE_FILE = "partida_salva.dat";

    public static void salvar(Partida partida) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(partida);
            System.out.println("[SAVE] Sucesso em " + SAVE_FILE);
        } catch (IOException e) {
            System.err.println("[SAVE] Erro: " + e.getMessage());
        }
    }

    public static Partida carregar() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.out.println("[LOAD] Nenhum save");
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Partida p = (Partida) ois.readObject();
            System.out.println("[LOAD] Carregado");
            return p;
        } catch (Exception e) {
            System.err.println("[LOAD] Erro: " + e.getMessage());
            return null;
        }
    }

    public static void limparSave() {
        File f = new File(SAVE_FILE);
        if (f.exists()) f.delete();
    }
}