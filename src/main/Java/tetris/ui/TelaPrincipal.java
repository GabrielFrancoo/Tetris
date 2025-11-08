package main.Java.tetris.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import main.Java.tetris.domain.Partida;
import main.Java.tetris.engine.GameEngine;
import main.Java.tetris.engine.InputHandler;

public class TelaPrincipal extends JFrame {
    private final Partida partida;
    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;
    private final GameEngine engine;
    private Timer timerUI;

    public TelaPrincipal(Partida partida) {
        super("Tetris - Projeto Acadêmico");
        this.partida = partida;

        engine = new GameEngine(partida);
        gamePanel = new GamePanel(partida.getTabuleiro(), partida.getPecaAtual());
        scorePanel = new ScorePanel();

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addKeyListener(new InputHandler(partida));
        timerUI = new Timer(300, e -> atualizarTela());
    }

    public void iniciar() {
        engine.iniciar();
        timerUI.start();
        setVisible(true);
    }

    private void atualizarTela() {
        gamePanel.atualizar(partida.getTabuleiro(), partida.getPecaAtual());
        scorePanel.atualizar(partida.getSistemaPontuacao());
        if (partida.isGameOver()) {
            timerUI.stop();
            JOptionPane.showMessageDialog(this,
                "Game Over!\nPontuação final: " + partida.getSistemaPontuacao().getPontos());
            dispose();
        }
    }
}
