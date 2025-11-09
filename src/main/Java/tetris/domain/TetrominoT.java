package tetris.domain;

import java.awt.Color;

public class TetrominoT extends Tetromino {
    public TetrominoT() {
        cor = Color.MAGENTA;
        forma = new boolean[][] {
            {false, true,  false, false},
            {true,  true,  true,  false},
            {false, false, false, false},
            {false, false, false, false}
        };
    }
}

