package main.Java.tetris.engine;

import main.Java.tetris.domain.Partida;
import main.Java.tetris.io.SaveManager;

public class GameEngine implements Runnable {
    private final Partida partida;
    private boolean rodando = false;
    private ThreadLoop threadLoop;

    public GameEngine(Partida partida) {
        this.partida = partida;
    }

    public void iniciar() {
        if (!rodando) {
            rodando = true;
            threadLoop = new ThreadLoop(this);
            threadLoop.start();
            System.out.println("[ENGINE] Loop iniciado");
        }
    }

    @Override
    public void run() {
        if (partida.isGameOver()) {
            parar();
            System.out.println("[ENGINE] Game Over!");
            return;
        }
        partida.tick();
    }

    public void parar() {
        rodando = false;
        if (threadLoop != null) threadLoop.parar();
        SaveManager.salvar(partida);
        System.out.println("[ENGINE] Loop encerrado e partida salva");
    }
}
