package main.Java.tetris.domain;

import java.awt.Color;

public class TetrominoI extends Tetromino {
    public TetrominoI() {
        cor = Color.CYAN;
        // Representação 4x4 (linha reta)
        forma = new boolean[][] {
            {false, false, false, false},
            {true,  true,  true,  true},
            {false, false, false, false},
            {false, false, false, false}
        };
    }
}
