package tetris.domain;

import java.awt.Color;

public class TetrominoJ extends Tetromino {
    public TetrominoJ() {
        cor = Color.BLUE.darker();
        forma = new boolean[][] {
            {true,  false, false, false},
            {true,  true,  true,  false},
            {false, false, false, false},
            {false, false, false, false}
        };
    }
}

