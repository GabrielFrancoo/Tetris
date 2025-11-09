package tetris.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import tetris.domain.Partida;
import tetris.engine.GameEngine;
import tetris.engine.InputHandler;

public class TelaPrincipal extends JFrame {
    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;
    private GameEngine engine;
    private Timer timerUI;
    private JLabel lblPausa;
    private Partida partida;
    private JPanel painelLateral;
    private JLabel lblJogador;
    private JLabel lblControles;
    private NextPiecePanel nextPiecePanel;

    public TelaPrincipal(Partida partida) {
        super("🎮 TETRIS - Jogo Clássico");
        this.partida = partida;

        engine = new GameEngine(partida);
        gamePanel = new GamePanel(partida.getTabuleiro(), partida.getPecaAtual());
        scorePanel = new ScorePanel();
        
        // Cria o label de pausa
        lblPausa = new JLabel("PAUSADO", SwingConstants.CENTER);
        lblPausa.setFont(new Font("Arial", Font.BOLD, 48));
        lblPausa.setForeground(Color.WHITE);
        lblPausa.setVisible(false);
        
        // Cria painel lateral com informações
        criarPainelLateral();
        
        // Ajusta a largura da barra de pontuação
        Dimension gameSize = gamePanel.getPreferredSize();
        scorePanel.setPreferredSize(new Dimension(gameSize.width, 90));
        scorePanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(50, 50, 50)));
        
        // Layout principal
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(20, 20, 20));
        
        // Painel central com jogo
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(20, 20, 20));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelCentral.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(lblPausa, BorderLayout.CENTER);
        
        // Adiciona componentes ao frame
        add(painelLateral, BorderLayout.EAST);
        add(painelCentral, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(20, 20, 20));

        // Adiciona o KeyListener ao GamePanel que é o componente focado
        InputHandler inputHandler = new InputHandler(partida, engine);
        gamePanel.addKeyListener(inputHandler);
        
        // Timer UI atualiza a cada 50ms para responsividade
        timerUI = new Timer(50, e -> atualizarTela());
        setFocusable(true); // Permite receber foco para eventos de teclado
    }
    
    private void criarPainelLateral() {
        painelLateral = new JPanel();
        painelLateral.setLayout(new BoxLayout(painelLateral, BoxLayout.Y_AXIS));
        painelLateral.setBackground(new Color(30, 30, 30));
        painelLateral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(50, 50, 50)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        painelLateral.setPreferredSize(new Dimension(250, 0));
        
        // Título
        JLabel titulo = new JLabel("🎮 TETRIS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(255, 200, 0));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        painelLateral.add(titulo);
        painelLateral.add(Box.createVerticalStrut(25));
        
        // Informações do jogador
        JLabel lblJogadorTitulo = new JLabel("JOGADOR:", SwingConstants.LEFT);
        lblJogadorTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblJogadorTitulo.setForeground(new Color(200, 200, 200));
        lblJogadorTitulo.setAlignmentX(LEFT_ALIGNMENT);
        painelLateral.add(lblJogadorTitulo);
        
        lblJogador = new JLabel(partida.getJogador().getNome());
        lblJogador.setFont(new Font("Consolas", Font.BOLD, 16));
        lblJogador.setForeground(new Color(255, 200, 0));
        lblJogador.setAlignmentX(LEFT_ALIGNMENT);
        painelLateral.add(lblJogador);
        painelLateral.add(Box.createVerticalStrut(20));
        
        // Separador
        JLabel separador = new JLabel("─────────────────────");
        separador.setForeground(new Color(100, 100, 100));
        separador.setAlignmentX(CENTER_ALIGNMENT);
        separador.setFont(new Font("Arial", Font.PLAIN, 12));
        painelLateral.add(separador);
        painelLateral.add(Box.createVerticalStrut(20));
        
        // Preview da próxima peça
        JLabel lblProximaTitulo = new JLabel("PRÓXIMA PEÇA", SwingConstants.CENTER);
        lblProximaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblProximaTitulo.setForeground(new Color(200, 200, 200));
        lblProximaTitulo.setAlignmentX(CENTER_ALIGNMENT);
        painelLateral.add(lblProximaTitulo);
        painelLateral.add(Box.createVerticalStrut(10));
        
        nextPiecePanel = new NextPiecePanel();
        nextPiecePanel.setAlignmentX(CENTER_ALIGNMENT);
        painelLateral.add(nextPiecePanel);
        painelLateral.add(Box.createVerticalStrut(20));
        
        // Separador
        JLabel separador2 = new JLabel("─────────────────────");
        separador2.setForeground(new Color(100, 100, 100));
        separador2.setAlignmentX(CENTER_ALIGNMENT);
        separador2.setFont(new Font("Arial", Font.PLAIN, 12));
        painelLateral.add(separador2);
        painelLateral.add(Box.createVerticalStrut(20));
        
        // Controles
        JLabel lblControlesTitulo = new JLabel("CONTROLES", SwingConstants.CENTER);
        lblControlesTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblControlesTitulo.setForeground(new Color(200, 200, 200));
        lblControlesTitulo.setAlignmentX(CENTER_ALIGNMENT);
        painelLateral.add(lblControlesTitulo);
        painelLateral.add(Box.createVerticalStrut(15));
        
        String controles = "<html><div style='text-align: left; line-height: 1.6;'>" +
            "<b>← →</b> Mover<br>" +
            "<b>↓</b> Acelerar<br>" +
            "<b>↑</b> Rotacionar<br>" +
            "<b>Espaço</b> Pausar" +
            "</div></html>";
        lblControles = new JLabel(controles);
        lblControles.setFont(new Font("Arial", Font.PLAIN, 14));
        lblControles.setForeground(new Color(220, 220, 220));
        lblControles.setAlignmentX(LEFT_ALIGNMENT);
        painelLateral.add(lblControles);
        painelLateral.add(Box.createVerticalGlue());
        
        // Rodapé
        JLabel rodape = new JLabel("Projeto Acadêmico", SwingConstants.CENTER);
        rodape.setFont(new Font("Arial", Font.ITALIC, 12));
        rodape.setForeground(new Color(120, 120, 120));
        rodape.setAlignmentX(CENTER_ALIGNMENT);
        painelLateral.add(rodape);
    }

    public void iniciar() {
        // Inicializa a primeira peça no tabuleiro
        partida.getTabuleiro().adicionarNovaPeca();
        // Atualiza o preview da próxima peça
        nextPiecePanel.atualizar(partida.getTabuleiro().getProximaPeca());
        
        engine.iniciar();
        timerUI.start();
        setVisible(true);
        // Garante que o GamePanel tenha foco para capturar teclas
        gamePanel.requestFocusInWindow();
    }

    private void atualizarTela() {
        // Atualiza animação de linhas se estiver ativa
        if (gamePanel.isAnimando()) {
            gamePanel.atualizarAnimacao();
            if (!gamePanel.isAnimando()) {
                // Animação terminou, finaliza remoção
                partida.finalizarRemocaoLinhas();
            }
        } else {
            // Verifica se há linhas para animar
            if (partida.isAguardandoAnimacao()) {
                gamePanel.iniciarAnimacaoLinhas();
            }
        }
        
        gamePanel.atualizar(partida.getTabuleiro(), partida.getPecaAtual());
        scorePanel.atualizar(partida.getSistemaPontuacao());
        nextPiecePanel.atualizar(partida.getTabuleiro().getProximaPeca());
        lblPausa.setVisible(engine.isPausado());
        if (partida.isGameOver()) {
            timerUI.stop();
            engine.parar();
            mostrarGameOver();
        }
    }
    
    private void mostrarGameOver() {
        int pontuacao = partida.getSistemaPontuacao().getPontos();
        int nivel = partida.getSistemaPontuacao().getNivel();
        
        GameOverDialog dialog = new GameOverDialog(this, pontuacao, nivel);
        dialog.setVisible(true);
        
        if (dialog.isReiniciar()) {
            reiniciarJogo();
        } else {
            dispose();
        }
    }
    
    private void reiniciarJogo() {
        // Fecha a janela atual
        dispose();
        
        // Cria nova partida com o mesmo jogador
        tetris.domain.Jogador jogador = partida.getJogador();
        tetris.domain.Partida novaPartida = new tetris.domain.Partida(jogador);
        
        // Cria e inicia nova janela
        TelaPrincipal novaTela = new TelaPrincipal(novaPartida);
        novaTela.iniciar();
    }

    public void alternarPausa() {
        engine.alternarPausa();
        lblPausa.setVisible(engine.isPausado());
    }
}

