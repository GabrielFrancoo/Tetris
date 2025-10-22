package com.model; //faz parte do package model

public class Board {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private int[][] grid; //representa o tabuleiro como uma grade 2D

    public Board() { //construtor do tabuleiro
       grid = new int[HEIGHT][WIDTH];//inicializa a grade do tabuleiro
    }

    public boolean isValidPosition(Tetromino piece, int x, int y) {
        //implementar validação de posição
        return true;
    }
    
}
