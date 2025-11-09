package tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import tetris.domain.Tabuleiro;
import tetris.domain.Tetromino;

public class GamePanel extends JPanel {
    private static final int TAM_CELULA = 40;
    private Tabuleiro tabuleiro;
    private Tetromino pecaAtual;
    private boolean[] linhasParaRemover;
    private int animacaoFlash = 0; // 0-100, controla intensidade do flash

    public GamePanel(Tabuleiro tabuleiro, Tetromino pecaAtual) {
        this.tabuleiro = tabuleiro;
        this.pecaAtual = pecaAtual;
        setPreferredSize(new Dimension(Tabuleiro.LARGURA * TAM_CELULA, Tabuleiro.ALTURA * TAM_CELULA));
        setBackground(new Color(15, 15, 15));
        setFocusable(true); // Permite receber foco para eventos de teclado
        setBorder(javax.swing.BorderFactory.createLineBorder(new Color(50, 50, 50), 2));
        
        // Garante que o painel recupere o foco quando clicado
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void atualizar(Tabuleiro tabuleiro, Tetromino pecaAtual) {
        this.tabuleiro = tabuleiro;
        this.pecaAtual = pecaAtual;
        this.linhasParaRemover = tabuleiro.getLinhasParaRemover();
        repaint();
        // Mantém o foco para capturar eventos de teclado
        if (!hasFocus()) {
            requestFocusInWindow();
        }
    }
    
    public void iniciarAnimacaoLinhas() {
        animacaoFlash = 100; // Inicia com flash máximo
    }
    
    public void atualizarAnimacao() {
        if (animacaoFlash > 0) {
            animacaoFlash -= 5; // Diminui gradualmente
            if (animacaoFlash < 0) animacaoFlash = 0;
            repaint();
        }
    }
    
    public boolean isAnimando() {
        return animacaoFlash > 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean[][] grid = tabuleiro.getGrid();
        
        // Desenha o grid
        for (int y = 0; y < Tabuleiro.ALTURA; y++) {
            boolean linhaCompleta = linhasParaRemover != null && linhasParaRemover[y];
            
            for (int x = 0; x < Tabuleiro.LARGURA; x++) {
                if (grid[y][x]) {
                    // Efeito de flash para linhas completas
                    if (linhaCompleta && animacaoFlash > 0) {
                        // Flash branco pulsante
                        int intensidade = (int)(255 * (animacaoFlash / 100.0));
                        g.setColor(new Color(intensidade, intensidade, intensidade));
                        g.fillRect(x * TAM_CELULA, y * TAM_CELULA, TAM_CELULA, TAM_CELULA);
                        // Borda brilhante
                        g.setColor(new Color(Math.min(255, intensidade + 50), 
                                            Math.min(255, intensidade + 50), 
                                            Math.min(255, intensidade + 50)));
                        g.drawRect(x * TAM_CELULA, y * TAM_CELULA, TAM_CELULA, TAM_CELULA);
                    } else {
                        // Gradiente normal para peças fixadas
                        g.setColor(new Color(80, 80, 80));
                        g.fillRect(x * TAM_CELULA, y * TAM_CELULA, TAM_CELULA, TAM_CELULA);
                        // Borda interna mais clara
                        g.setColor(new Color(120, 120, 120));
                        g.fillRect(x * TAM_CELULA + 2, y * TAM_CELULA + 2, TAM_CELULA - 4, TAM_CELULA - 4);
                        // Borda externa
                        g.setColor(new Color(40, 40, 40));
                        g.drawRect(x * TAM_CELULA, y * TAM_CELULA, TAM_CELULA, TAM_CELULA);
                    }
                }
            }
        }

        if (pecaAtual != null) {
            boolean[][] forma = pecaAtual.getForma();
            Color corPeca = pecaAtual.getCor();
            for (int i = 0; i < forma.length; i++)
                for (int j = 0; j < forma[i].length; j++)
                    if (forma[i][j]) {
                        int px = (pecaAtual.getPosicao().getX() + j) * TAM_CELULA;
                        int py = (pecaAtual.getPosicao().getY() + i) * TAM_CELULA;
                        if (py >= 0) { // não desenha acima do topo
                            // Cor principal
                            g.setColor(corPeca);
                            g.fillRect(px, py, TAM_CELULA, TAM_CELULA);
                            // Brilho interno
                            g.setColor(new Color(
                                Math.min(255, corPeca.getRed() + 40),
                                Math.min(255, corPeca.getGreen() + 40),
                                Math.min(255, corPeca.getBlue() + 40)
                            ));
                            g.fillRect(px + 2, py + 2, TAM_CELULA - 4, 8);
                            // Borda
                            g.setColor(corPeca.darker().darker());
                            g.drawRect(px, py, TAM_CELULA, TAM_CELULA);
                        }
                    }
        }

        // Grade mais sutil
        g.setColor(new Color(40, 40, 40));
        for (int x = 0; x <= Tabuleiro.LARGURA; x++)
            g.drawLine(x * TAM_CELULA, 0, x * TAM_CELULA, Tabuleiro.ALTURA * TAM_CELULA);
        for (int y = 0; y <= Tabuleiro.ALTURA; y++)
            g.drawLine(0, y * TAM_CELULA, Tabuleiro.LARGURA * TAM_CELULA, y * TAM_CELULA);
    }
}

