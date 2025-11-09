package tetris.domain;

import java.awt.Color;

/**
 * Classe base para Tetrominos. Usa matrizes 4x4 para formas e fornece
 * rotação básica (transpose + reverse) para rotação CW/CCW.
 */
public class Tetromino {
    protected boolean[][] forma; // 4x4
    protected Color cor;
    protected Posicao posicao;

    public Tetromino() {
        forma = new boolean[4][4];
        posicao = new Posicao(3, -1); // posição inicial (acima do topo)
    }

    public void rotacionar() {
        forma = rotateCW(forma);
    }

    public void rotacionarReverso() {
        forma = rotateCCW(forma);
    }

    public void moverBaixo() {
        posicao = posicao.moverParaBaixo();
    }

    public void moverEsquerda() {
        posicao = posicao.moverEsquerda();
    }

    public void moverDireita() {
        posicao = posicao.moverDireita();
    }

    public boolean[][] getForma() { return forma; }
    public Color getCor() { return cor; }
    public Posicao getPosicao() { return posicao; }
    public void setPosicao(Posicao p) { this.posicao = p; }

    // Helper: rotaciona matriz 4x4 no sentido horário
    private boolean[][] rotateCW(boolean[][] m) {
        int n = m.length;
        boolean[][] r = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                r[j][n - 1 - i] = m[i][j];
        return r;
    }

    // Helper: rotaciona matriz 4x4 no sentido anti-horário
    private boolean[][] rotateCCW(boolean[][] m) {
        int n = m.length;
        boolean[][] r = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                r[n - 1 - j][i] = m[i][j];
        return r;
    }
}

