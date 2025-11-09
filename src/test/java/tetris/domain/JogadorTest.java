package tetris.domain;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class JogadorTest {

    @Test
    public void testCriacaoComNome() {
        Jogador jogador = new Jogador("João");
        assertNotNull(jogador.getId());
        assertEquals("João", jogador.getNome());
    }

    @Test
    public void testCriacaoComIdENome() {
        UUID id = UUID.randomUUID();
        Jogador jogador = new Jogador(id, "Maria");
        assertEquals(id, jogador.getId());
        assertEquals("Maria", jogador.getNome());
    }

    @Test
    public void testSetNome() {
        Jogador jogador = new Jogador("João");
        jogador.setNome("João Silva");
        assertEquals("João Silva", jogador.getNome());
    }

    @Test
    public void testIdUnico() {
        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");
        assertNotEquals(jogador1.getId(), jogador2.getId());
    }

    @Test
    public void testGetId() {
        UUID id = UUID.randomUUID();
        Jogador jogador = new Jogador(id, "Teste");
        assertEquals(id, jogador.getId());
    }
}

