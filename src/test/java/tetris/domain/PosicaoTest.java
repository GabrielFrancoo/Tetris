package tetris.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PosicaoTest {

    @Test
    public void testCriacao() {
        Posicao p = new Posicao(5, 10);
        assertEquals(5, p.getX());
        assertEquals(10, p.getY());
    }

    @Test
    public void testMoverParaBaixo() {
        Posicao p = new Posicao(5, 10);
        Posicao nova = p.moverParaBaixo();
        assertEquals(5, nova.getX());
        assertEquals(11, nova.getY());
        // Original não deve mudar (imutável)
        assertEquals(10, p.getY());
    }

    @Test
    public void testMoverEsquerda() {
        Posicao p = new Posicao(5, 10);
        Posicao nova = p.moverEsquerda();
        assertEquals(4, nova.getX());
        assertEquals(10, nova.getY());
        assertEquals(5, p.getX());
    }

    @Test
    public void testMoverDireita() {
        Posicao p = new Posicao(5, 10);
        Posicao nova = p.moverDireita();
        assertEquals(6, nova.getX());
        assertEquals(10, nova.getY());
        assertEquals(5, p.getX());
    }
}

