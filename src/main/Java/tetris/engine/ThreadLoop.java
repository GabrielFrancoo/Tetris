package tetris.engine;

public class ThreadLoop extends Thread {
    private final Runnable tarefa;
    private volatile boolean rodando = true;
    private volatile int delay = 300; // Delay inicial (ms) - será ajustado dinamicamente pelo GameEngine

    public ThreadLoop(Runnable tarefa) {
        this.tarefa = tarefa;
        setDaemon(true); // Thread daemon para não impedir encerramento da JVM
    }

    @Override
    public void run() {
        while (rodando) {
            tarefa.run();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void parar() { 
        rodando = false; 
        interrupt(); // Interrompe o sleep se estiver esperando
    }
    
    public void setDelay(int delay) { 
        if (delay > 0) {
            this.delay = delay; 
        }
    }
}

