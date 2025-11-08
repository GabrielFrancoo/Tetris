package main.Java.tetris.domain;

import java.awt.Color;

public class TetrominoO extends Tetromino {
    public TetrominoO() {
        cor = Color.YELLOW;
        forma = new boolean[][] {
            {false, true,  true,  false},
            {false, true,  true,  false},
            {false, false, false, false},
            {false, false, false, false}
        };
    }

    // O não rotaciona (mantém a mesma forma)
    @Override
    public void rotacionarCW() { /* noop */ }

    @Override
    public void rotacionarCCW() { /* noop */ }
}
