package main.Java.tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Java.tetris.domain.Tabuleiro;
import main.Java.tetris.domain.Tetromino;

public class GamePanel extends JPanel {
    private static final int TAM_CELULA = 30;
    private Tabuleiro tabuleiro;
    private Tetromino pecaAtual;

    public GamePanel(Tabuleiro tabuleiro, Tetromino pecaAtual) {
        this.tabuleiro = tabuleiro;
        this.pecaAtual = pecaAtual;
        setPreferredSize(new Dimension(Tabuleiro.LARGURA * TAM_CELULA, Tabuleiro.ALTURA * TAM_CELULA));
        setBackground(Color.BLACK);
    }

    public void atualizar(Tabuleiro tabuleiro, Tetromino pecaAtual) {
        this.tabuleiro = tabuleiro;
        this.pecaAtual = pecaAtual;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean[][] grid = tabuleiro.getGrid();
        for (int y = 0; y < Tabuleiro.ALTURA; y++) {
            for (int x = 0; x < Tabuleiro.LARGURA; x++) {
                if (grid[y][x]) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x * TAM_CELULA, y * TAM_CELULA, TAM_CELULA, TAM_CELULA);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x * TAM_CELULA, y * TAM_CELULA, TAM_CELULA, TAM_CELULA);
                }
            }
        }

        if (pecaAtual != null) {
            boolean[][] forma = pecaAtual.getForma();
            g.setColor(pecaAtual.getCor());
            for (int i = 0; i < forma.length; i++)
                for (int j = 0; j < forma[i].length; j++)
                    if (forma[i][j]) {
                        int px = (pecaAtual.getPosicao().getX() + j) * TAM_CELULA;
                        int py = (pecaAtual.getPosicao().getY() + i) * TAM_CELULA;
                        if (py >= 0) { // não desenha acima do topo
                            g.fillRect(px, py, TAM_CELULA, TAM_CELULA);
                            g.setColor(Color.DARK_GRAY);
                            g.drawRect(px, py, TAM_CELULA, TAM_CELULA);
                            g.setColor(pecaAtual.getCor());
                        }
                    }
        }

        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x <= Tabuleiro.LARGURA; x++)
            g.drawLine(x * TAM_CELULA, 0, x * TAM_CELULA, Tabuleiro.ALTURA * TAM_CELULA);
        for (int y = 0; y <= Tabuleiro.ALTURA; y++)
            g.drawLine(0, y * TAM_CELULA, Tabuleiro.LARGURA * TAM_CELULA, y * TAM_CELULA);
    }
}
