package tetris.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tetris.domain.Partida;

public class InputHandler implements KeyListener {
    private final Partida partida;
    private final GameEngine engine;

    public InputHandler(Partida partida, GameEngine engine) {
        this.partida = partida;
        this.engine = engine;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Permite pausar mesmo se o jogo ainda não iniciou completamente
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            engine.alternarPausa();
            return;
        }
        
        // Bloqueia outras ações se game over ou não estiver rodando
        if (partida.isGameOver() || !engine.isRodando()) {
            return;
        }
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> partida.tentarMoverEsquerda();
            case KeyEvent.VK_RIGHT -> partida.tentarMoverDireita();
            case KeyEvent.VK_DOWN -> partida.tentarMoverBaixo();
            case KeyEvent.VK_UP -> partida.tentarRotacionar();
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {}

    @Override 
    public void keyTyped(KeyEvent e) {}
}

