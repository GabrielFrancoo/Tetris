package tetris.domain;

import java.awt.Color;

public class TetrominoL extends Tetromino {
    public TetrominoL() {
        cor = Color.ORANGE;
        forma = new boolean[][] {
            {false, false, true,  false},
            {true,  true,  true,  false},
            {false, false, false, false},
            {false, false, false, false}
        };
    }
}

