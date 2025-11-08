package main.Java.tetris.engine;

public class ThreadLoop extends Thread {
    private final Runnable tarefa;
    private volatile boolean rodando = true;
    private int delay = 500;

    public ThreadLoop(Runnable tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public void run() {
        while (rodando) {
            tarefa.run();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void parar() { rodando = false; }
    public void setDelay(int delay) { this.delay = delay; }
}
