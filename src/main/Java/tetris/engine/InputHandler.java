package main.Java.tetris.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Java.tetris.domain.Partida;

public class InputHandler implements KeyListener {
    private final Partida partida;

    public InputHandler(Partida partida) {
        this.partida = partida;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> partida.tentarMoverEsquerda();
            case KeyEvent.VK_RIGHT -> partida.tentarMoverDireita();
            case KeyEvent.VK_DOWN -> partida.tentarMoverBaixo();
            case KeyEvent.VK_UP -> partida.tentarRotacionar();
            case KeyEvent.VK_SPACE -> System.out.println("[INPUT] Pausa (não implementada)");
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
