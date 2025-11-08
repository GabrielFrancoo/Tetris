package main.Java.tetris.domain;

import java.awt.Color;

public class TetrominoZ extends Tetromino {
    public TetrominoZ() {
        cor = Color.RED;
        forma = new boolean[][] {
            {true,  true,  false, false},
            {false, true,  true,  false},
            {false, false, false, false},
            {false, false, false, false}
        };
    }
}
