package tetris.engine;

import tetris.domain.Partida;

public class GameEngine implements Runnable {
    private final Partida partida;
    private boolean rodando = false;
    private boolean pausado = false;
    private ThreadLoop threadLoop;

    public GameEngine(Partida partida) {
        this.partida = partida;
    }

    public void iniciar() {
        if (!rodando) {
            rodando = true;
            threadLoop = new ThreadLoop(this);
            threadLoop.start();
            // Define velocidade inicial
            atualizarVelocidade();
            System.out.println("[ENGINE] Loop iniciado");
        }
    }

    @Override
    public void run() {
        if (partida.isGameOver()) {
            parar();
            System.out.println("[ENGINE] Game Over!");
            return;
        }
        // Não atualiza se estiver pausado
        if (!pausado) {
            partida.tick();
            // Atualiza velocidade baseado no nível
            atualizarVelocidade();
        }
    }

    private void atualizarVelocidade() {
        if (threadLoop != null) {
            int nivel = partida.getSistemaPontuacao().getNivel();
            // Velocidade progressiva: nível 1 = 300ms, diminui 40ms por nível, mínimo 50ms
            // Exemplo: Nível 1=300ms, Nível 5=140ms, Nível 7=60ms
            int novoDelay = Math.max(50, 300 - (nivel - 1) * 40);
            threadLoop.setDelay(novoDelay);
        }
    }

    public void parar() {
        rodando = false;
        if (threadLoop != null) {
            threadLoop.parar();
        }
        System.out.println("[ENGINE] Loop encerrado");
    }

    public void alternarPausa() {
        pausado = !pausado;
        System.out.println("[ENGINE] Jogo " + (pausado ? "pausado" : "despausado"));
    }

    public boolean isPausado() {
        return pausado;
    }
    
    public boolean isRodando() {
        return rodando;
    }
}

