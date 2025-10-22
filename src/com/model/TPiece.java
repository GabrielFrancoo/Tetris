package com.model;

import java.awt.Color;

public class TPiece {
    public TPiece() {
        // Constructor logic for T-shaped Tetris piece
        super();
        shape = new int[][]{
        {0,1,0},
        {1,1,1},
    };
    color = Color.MAGENTA;
    }
    
    @Override
    public void rotate() {
        //implementa rotação específica para a peça T
    }   
}
