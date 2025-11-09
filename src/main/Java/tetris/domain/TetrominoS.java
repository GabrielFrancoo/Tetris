package tetris.domain;

import java.awt.Color;

public class TetrominoS extends Tetromino {
    public TetrominoS() {
        cor = Color.GREEN;
        forma = new boolean[][] {
            {false, true,  true,  false},
            {true,  true,  false, false},
            {false, false, false, false},
            {false, false, false, false}
        };
    }
}

