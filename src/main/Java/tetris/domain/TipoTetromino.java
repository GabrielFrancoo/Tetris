package tetris.domain;

import java.util.Random;

public enum TipoTetromino {
    I, O, T, S, Z, J, L;

    private static final Random random = new Random();

    public static TipoTetromino aleatorio() {
        return values()[random.nextInt(values().length)];
    }

    public Tetromino criar() {
        switch (this) {
            case I: return new TetrominoI();
            case O: return new TetrominoO();
            case T: return new TetrominoT();
            case S: return new TetrominoS();
            case Z: return new TetrominoZ();
            case J: return new TetrominoJ();
            case L: return new TetrominoL();
            default: throw new IllegalStateException("Tipo de Tetromino desconhecido: " + this);
        }
    }
}

