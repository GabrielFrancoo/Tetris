package main.Java.tetris.domain;

import java.util.Arrays;

public class Tabuleiro {
    public static final int LARGURA = 10;
    public static final int ALTURA = 20;
    private final boolean[][] grid = new boolean[ALTURA][LARGURA];

    public boolean[][] getGrid() { return grid; }

    public boolean posicaoValida(Tetromino t) {
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

    public int eliminarLinhasCompletas() {
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

    private boolean linhaCompleta(int y) {
        for (boolean b : grid[y]) if (!b) return false;
        return true;
    }

    private void eliminarLinha(int y) {
        for (int i = y; i > 0; i--)
            System.arraycopy(grid[i - 1], 0, grid[i], 0, LARGURA);
        Arrays.fill(grid[0], false);
    }
}
