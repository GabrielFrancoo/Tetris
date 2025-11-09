package tetris.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaPontuacaoTest {

    private SistemaPontuacao sistema;

    @Before
    public void setUp() {
        sistema = new SistemaPontuacao();
    }

    @Test
    public void testInicializacao() {
        assertEquals(0, sistema.getPontos());
        assertEquals(1, sistema.getNivel());
    }

    @Test
    public void testAdicionarUmaLinha() {
        sistema.adicionarLinhas(1);
        assertEquals(40, sistema.getPontos()); // 40 * nível 1
        assertEquals(1, sistema.getNivel());
    }

    @Test
    public void testAdicionarDuasLinhas() {
        sistema.adicionarLinhas(2);
        assertEquals(100, sistema.getPontos()); // 100 * nível 1
    }

    @Test
    public void testAdicionarTresLinhas() {
        sistema.adicionarLinhas(3);
        assertEquals(300, sistema.getPontos()); // 300 * nível 1
    }

    @Test
    public void testAdicionarQuatroLinhas() {
        sistema.adicionarLinhas(4);
        assertEquals(1200, sistema.getPontos()); // 1200 * nível 1
    }

    @Test
    public void testSubirNivel() {
        // Adiciona 500 pontos para subir de nível
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        sistema.adicionarLinhas(1); // 40 pontos
        
        // Total: 560 pontos, deve estar no nível 2
        assertTrue(sistema.getNivel() >= 2);
    }

    @Test
    public void testPontosComMultiplosNiveis() {
        // Força subir para nível 2
        for (int i = 0; i < 15; i++) {
            sistema.adicionarLinhas(1);
        }
        
        int nivelAntes = sistema.getNivel();
        int pontosAntes = sistema.getPontos();
        sistema.adicionarLinhas(1);
        // Deve multiplicar pelo nível atual
        assertTrue(sistema.getPontos() > pontosAntes);
        assertTrue(sistema.getNivel() >= nivelAntes);
    }

    @Test
    public void testAdicionarZeroLinhas() {
        sistema.adicionarLinhas(0);
        assertEquals(0, sistema.getPontos());
    }

    @Test
    public void testAdicionarLinhasInvalidas() {
        sistema.adicionarLinhas(5); // Mais de 4 linhas
        assertEquals(0, sistema.getPontos()); // Não deve adicionar pontos
    }
}

