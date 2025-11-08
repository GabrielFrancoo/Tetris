package main.Java.tetris.domain;

import java.io.Serializable;

public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Jogador jogador;
    private final Tabuleiro tabuleiro;
    private final SistemaPontuacao sistemaPontuacao;
    private Tetromino pecaAtual;
    private boolean gameOver = false;

    public Partida(Jogador jogador) {
        this.jogador = jogador;
        this.tabuleiro = new Tabuleiro();
        this.sistemaPontuacao = new SistemaPontuacao();
        this.pecaAtual = Tetromino.gerarAleatorio();
    }

    public synchronized void tick() {
        if (gameOver) return;

        // tenta mover para baixo
        pecaAtual.moverBaixo();
        if (!tabuleiro.posicaoValida(pecaAtual)) {
            // reverte subida de fato: moverBaixo já colocou abaixo, então volta uma linha para fixar corretamente
            pecaAtual.setPosicao(new Posicao(pecaAtual.getPosicao().getX(), pecaAtual.getPosicao().getY() - 1));
            tabuleiro.fixarPeca(pecaAtual);
            int linhas = tabuleiro.eliminarLinhasCompletas();
            sistemaPontuacao.adicionarLinhas(linhas);

            pecaAtual = Tetromino.gerarAleatorio();
            if (!tabuleiro.posicaoValida(pecaAtual)) gameOver = true;
        }
    }

    public Jogador getJogador() { return jogador; }
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public SistemaPontuacao getSistemaPontuacao() { return sistemaPontuacao; }
    public Tetromino getPecaAtual() { return pecaAtual; }
    public boolean isGameOver() { return gameOver; }

    // Métodos auxiliares para entrada (tentativa de mover/rotacionar com validação)
    public synchronized void tentarMoverEsquerda() {
        pecaAtual.moverEsquerda();
        if (!tabuleiro.posicaoValida(pecaAtual)) pecaAtual.moverDireita();
    }

    public synchronized void tentarMoverDireita() {
        pecaAtual.moverDireita();
        if (!tabuleiro.posicaoValida(pecaAtual)) pecaAtual.moverEsquerda();
    }

    public synchronized void tentarMoverBaixo() {
        pecaAtual.moverBaixo();
        if (!tabuleiro.posicaoValida(pecaAtual)) {
            // reverte e fixa (como no tick)
            pecaAtual.setPosicao(new Posicao(pecaAtual.getPosicao().getX(), pecaAtual.getPosicao().getY() - 1));
            tabuleiro.fixarPeca(pecaAtual);
            int linhas = tabuleiro.eliminarLinhasCompletas();
            sistemaPontuacao.adicionarLinhas(linhas);
            pecaAtual = Tetromino.gerarAleatorio();
            if (!tabuleiro.posicaoValida(pecaAtual)) gameOver = true;
        }
    }

    public synchronized void tentarRotacionar() {
        pecaAtual.rotacionarCW();
        if (!tabuleiro.posicaoValida(pecaAtual)) {
            // tenta simples kick: desloca +/-1 horizontalmente
            pecaAtual.moverDireita();
            if (!tabuleiro.posicaoValida(pecaAtual)) {
                pecaAtual.moverEsquerda();
                pecaAtual.moverEsquerda();
                if (!tabuleiro.posicaoValida(pecaAtual)) {
                    // reverte tudo
                    pecaAtual.moverDireita();
                    pecaAtual.rotacionarCCW();
                }
            }
        }
    }
}
