package tetris.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import tetris.domain.Partida;
import tetris.engine.GameEngine;
import tetris.engine.InputHandler;

public class TelaPrincipal extends JFrame {
    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;
    private final GameEngine engine;
    private Timer timerUI;
    private JLabel lblPausa;
    private final Partida partida;

    public TelaPrincipal(Partida partida) {
        super("Tetris - Projeto Acadêmico");
        this.partida = partida;

        engine = new GameEngine(partida);
        gamePanel = new GamePanel(partida.getTabuleiro(), partida.getPecaAtual());
        scorePanel = new ScorePanel();
        
        // Cria o label de pausa
        lblPausa = new JLabel("PAUSADO", SwingConstants.CENTER);
        lblPausa.setFont(new Font("Arial", Font.BOLD, 48));
        lblPausa.setForeground(Color.WHITE);
        lblPausa.setVisible(false);
        
        // Ajusta a largura da barra de pontuação para acompanhar a largura do GamePanel
        Dimension gameSize = gamePanel.getPreferredSize();
        scorePanel.setPreferredSize(new Dimension(gameSize.width, 60));
        // Linha separadora superior para destacar a barra
        scorePanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
        
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(lblPausa, BorderLayout.CENTER);
        // Coloca a barra de pontuação na parte inferior (SOUTH)
        add(scorePanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        // Adiciona o KeyListener ao GamePanel que é o componente focado
        gamePanel.addKeyListener(new InputHandler(partida, engine));
        // Timer UI atualiza a cada 50ms para responsividade
        timerUI = new Timer(50, e -> atualizarTela());
        setFocusable(true); // Permite receber foco para eventos de teclado
    }

    public void iniciar() {
        // Inicializa a primeira peça no tabuleiro
        partida.getTabuleiro().adicionarNovaPeca();
        
        engine.iniciar();
        timerUI.start();
        setVisible(true);
        // Garante que o GamePanel tenha foco para capturar teclas
        gamePanel.requestFocusInWindow();
    }

    private void atualizarTela() {
        gamePanel.atualizar(partida.getTabuleiro(), partida.getPecaAtual());
        scorePanel.atualizar(partida.getSistemaPontuacao());
        lblPausa.setVisible(engine.isPausado());
        if (partida.isGameOver()) {
            timerUI.stop();
            engine.parar();
            JOptionPane.showMessageDialog(this,
                "Game Over!\nPontuação final: " + partida.getSistemaPontuacao().getPontos());
            dispose();
        }
    }

    public void alternarPausa() {
        engine.alternarPausa();
        lblPausa.setVisible(engine.isPausado());
    }
}

