package tetris.domain;

import java.util.Arrays;

public class Tabuleiro {
    public static final int LARGURA = 10;
    public static final int ALTURA = 20;
    private final boolean[][] grid = new boolean[ALTURA][LARGURA];
    private Tetromino pecaAtual;

    public boolean[][] getGrid() { 
        return grid; 
    }

    public boolean posicaoValida(Tetromino t) {
        if (t == null) return false;
        
        boolean[][] forma = t.getForma();
        Posicao p = t.getPosicao();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j]) {
                    int x = p.getX() + j;
                    int y = p.getY() + i;

                    if (x < 0 || x >= LARGURA || y >= ALTURA)
                        return false;
                    if (y >= 0 && grid[y][x])
                        return false;
                }
            }
        }
        return true;
    }

    public void fixarPeca(Tetromino t) {
        if (t == null) return;
        
        boolean[][] forma = t.getForma();
        Posicao p = t.getPosicao();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j]) {
                    int x = p.getX() + j;
                    int y = p.getY() + i;
                    if (y >= 0 && y < ALTURA && x >= 0 && x < LARGURA)
                        grid[y][x] = true;
                }
            }
        }
    }

    private boolean linhaCompleta(int y) {
        for (boolean b : grid[y]) if (!b) return false;
        return true;
    }

    private void eliminarLinha(int y) {
        for (int i = y; i > 0; i--)
            System.arraycopy(grid[i - 1], 0, grid[i], 0, LARGURA);
        Arrays.fill(grid[0], false);
    }

    public Tetromino getPecaAtual() {
        return pecaAtual;
    }

    public boolean adicionarNovaPeca() {
        pecaAtual = TipoTetromino.aleatorio().criar();
        pecaAtual.setPosicao(new Posicao(LARGURA / 2 - 2, -1));
        return posicaoValida(pecaAtual);
    }

    public void fixarPecaAtual() {
        if (pecaAtual != null) {
            fixarPeca(pecaAtual);
            pecaAtual = null;
        }
    }

    public int removerLinhasCompletas() {
        int eliminadas = 0;
        for (int y = ALTURA - 1; y >= 0; y--) {
            if (linhaCompleta(y)) {
                eliminarLinha(y);
                eliminadas++;
                y++; // rechecagem após shift
            }
        }
        return eliminadas;
    }

    public boolean moverPecaAtualEsquerda() {
        if (pecaAtual == null) return false;
        Posicao antiga = pecaAtual.getPosicao();
        pecaAtual.setPosicao(new Posicao(antiga.getX() - 1, antiga.getY()));
        if (!posicaoValida(pecaAtual)) {
            pecaAtual.setPosicao(antiga);
            return false;
        }
        return true;
    }

    public boolean moverPecaAtualDireita() {
        if (pecaAtual == null) return false;
        Posicao antiga = pecaAtual.getPosicao();
        pecaAtual.setPosicao(new Posicao(antiga.getX() + 1, antiga.getY()));
        if (!posicaoValida(pecaAtual)) {
            pecaAtual.setPosicao(antiga);
            return false;
        }
        return true;
    }

    public boolean moverPecaAtualBaixo() {
        if (pecaAtual == null) return false;
        Posicao antiga = pecaAtual.getPosicao();
        pecaAtual.setPosicao(new Posicao(antiga.getX(), antiga.getY() + 1));
        if (!posicaoValida(pecaAtual)) {
            pecaAtual.setPosicao(antiga);
            return false;
        }
        return true;
    }

    public boolean rotacionarPecaAtual() {
        if (pecaAtual == null) return false;
        pecaAtual.rotacionar();
        if (!posicaoValida(pecaAtual)) {
            pecaAtual.rotacionarReverso();
            return false;
        }
        return true;
    }
}

