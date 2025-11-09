package tetris.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PartidaTest {

    private Partida partida;
    private Jogador jogador;

    @Before
    public void setUp() {
        jogador = new Jogador("Teste");
        partida = new Partida(jogador);
    }

    @Test
    public void testInicializacao() {
        assertNotNull(partida.getJogador());
        assertNotNull(partida.getTabuleiro());
        assertNotNull(partida.getSistemaPontuacao());
        assertFalse(partida.isFinalizada());
        assertFalse(partida.isGameOver());
    }

    @Test
    public void testTentarMoverEsquerda() {
        partida.getTabuleiro().adicionarNovaPeca();
        Tetromino peca = partida.getPecaAtual();
        int xInicial = peca.getPosicao().getX();
        
        boolean movido = partida.tentarMoverEsquerda();
        assertTrue(movido);
        assertEquals(xInicial - 1, peca.getPosicao().getX());
    }

    @Test
    public void testTentarMoverDireita() {
        partida.getTabuleiro().adicionarNovaPeca();
        Tetromino peca = partida.getPecaAtual();
        int xInicial = peca.getPosicao().getX();
        
        boolean movido = partida.tentarMoverDireita();
        assertTrue(movido);
        assertEquals(xInicial + 1, peca.getPosicao().getX());
    }

    @Test
    public void testTentarMoverBaixo() {
        partida.getTabuleiro().adicionarNovaPeca();
        Tetromino peca = partida.getPecaAtual();
        int yInicial = peca.getPosicao().getY();
        
        boolean movido = partida.tentarMoverBaixo();
        assertTrue(movido);
        assertEquals(yInicial + 1, peca.getPosicao().getY());
    }

    @Test
    public void testTentarRotacionar() {
        partida.getTabuleiro().adicionarNovaPeca();
        boolean rotacionado = partida.tentarRotacionar();
        assertTrue(rotacionado);
    }

    @Test
    public void testTick() {
        partida.getTabuleiro().adicionarNovaPeca();
        int pontosInicial = partida.getSistemaPontuacao().getPontos();
        
        // Executa vários ticks
        for (int i = 0; i < 10; i++) {
            partida.tick();
        }
        
        // Verifica se o jogo continua rodando
        assertFalse(partida.isGameOver());
        // Pontos podem ter aumentado se linhas foram completadas
        assertTrue(partida.getSistemaPontuacao().getPontos() >= pontosInicial);
    }

    @Test
    public void testSetFinalizada() {
        partida.setFinalizada(true);
        assertTrue(partida.isFinalizada());
    }

    @Test
    public void testGetPecaAtual() {
        partida.getTabuleiro().adicionarNovaPeca();
        assertNotNull(partida.getPecaAtual());
    }

    @Test
    public void testGetPecaAtualNull() {
        // Sem adicionar peça, deve retornar null
        assertNull(partida.getPecaAtual());
    }
}

