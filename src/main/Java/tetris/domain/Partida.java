package tetris.domain;

import java.io.Serializable;

public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Jogador jogador;
    private final Tabuleiro tabuleiro;
    private final SistemaPontuacao sistemaPontuacao;
    private boolean finalizada;
    private boolean gameOver;

    public Partida(Jogador jogador) {
        this.jogador = jogador;
        this.tabuleiro = new Tabuleiro();
        this.sistemaPontuacao = new SistemaPontuacao();
        this.finalizada = false;
        this.gameOver = false;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public SistemaPontuacao getSistemaPontuacao() {
        return sistemaPontuacao;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean aguardandoAnimacao = false;
    private int linhasMarcadas = 0;
    
    public void tick() {
        if (aguardandoAnimacao) {
            return; // Aguarda animação terminar
        }
        
        if (!tentarMoverBaixo()) {
            tabuleiro.fixarPecaAtual();
            linhasMarcadas = tabuleiro.marcarLinhasCompletas();
            if (linhasMarcadas > 0) {
                aguardandoAnimacao = true;
                // A animação será finalizada pela UI
            } else {
                if (!tabuleiro.adicionarNovaPeca()) {
                    gameOver = true;
                }
            }
        }
    }
    
    public void finalizarRemocaoLinhas() {
        if (aguardandoAnimacao && linhasMarcadas > 0) {
            tabuleiro.removerLinhasMarcadas();
            sistemaPontuacao.adicionarLinhas(linhasMarcadas);
            aguardandoAnimacao = false;
            linhasMarcadas = 0;
            if (!tabuleiro.adicionarNovaPeca()) {
                gameOver = true;
            }
        }
    }
    
    public boolean isAguardandoAnimacao() {
        return aguardandoAnimacao;
    }
    
    public int getLinhasMarcadas() {
        return linhasMarcadas;
    }

    public boolean tentarMoverEsquerda() {
        return tabuleiro.moverPecaAtualEsquerda();
    }

    public boolean tentarMoverDireita() {
        return tabuleiro.moverPecaAtualDireita();
    }

    public boolean tentarMoverBaixo() {
        return tabuleiro.moverPecaAtualBaixo();
    }

    public boolean tentarRotacionar() {
        return tabuleiro.rotacionarPecaAtual();
    }

    public Tetromino getPecaAtual() {
        return tabuleiro.getPecaAtual();
    }
}

