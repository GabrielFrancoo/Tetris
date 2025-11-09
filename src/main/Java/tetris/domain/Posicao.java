package tetris.domain;

public final class Posicao {
    private final int x;
    private final int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Posicao moverParaBaixo() {
        return new Posicao(x, y + 1);
    }

    public Posicao moverEsquerda() {
        return new Posicao(x - 1, y);
    }

    public Posicao moverDireita() {
        return new Posicao(x + 1, y);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

