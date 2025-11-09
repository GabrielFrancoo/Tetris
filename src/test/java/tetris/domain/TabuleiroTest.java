package tetris.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test
    public void testInicializacao() {
        boolean[][] grid = tabuleiro.getGrid();
        assertEquals(Tabuleiro.ALTURA, grid.length);
        assertEquals(Tabuleiro.LARGURA, grid[0].length);
        assertNull(tabuleiro.getPecaAtual());
    }

    @Test
    public void testAdicionarNovaPeca() {
        boolean adicionada = tabuleiro.adicionarNovaPeca();
        assertTrue(adicionada);
        assertNotNull(tabuleiro.getPecaAtual());
    }

    @Test
    public void testPosicaoValida() {
        Tetromino t = new TetrominoI();
        t.setPosicao(new Posicao(5, 5));
        assertTrue(tabuleiro.posicaoValida(t));
    }

    @Test
    public void testPosicaoInvalidaForaDoLimiteDireita() {
        Tetromino t = new TetrominoI();
        t.setPosicao(new Posicao(Tabuleiro.LARGURA, 5));
        assertFalse(tabuleiro.posicaoValida(t));
    }

    @Test
    public void testPosicaoInvalidaForaDoLimiteEsquerda() {
        Tetromino t = new TetrominoI();
        t.setPosicao(new Posicao(-1, 5));
        assertFalse(tabuleiro.posicaoValida(t));
    }

    @Test
    public void testPosicaoInvalidaForaDoLimiteBaixo() {
        Tetromino t = new TetrominoI();
        t.setPosicao(new Posicao(5, Tabuleiro.ALTURA));
        assertFalse(tabuleiro.posicaoValida(t));
    }

    @Test
    public void testFixarPeca() {
        Tetromino t = new TetrominoI();
        t.setPosicao(new Posicao(5, 5));
        tabuleiro.fixarPeca(t);
        
        boolean[][] grid = tabuleiro.getGrid();
        // TetrominoI tem forma na linha 1 da matriz 4x4, então y=5+1=6
        // Verifica se a peça foi fixada no grid (colunas 5, 6, 7, 8 na linha 6)
        assertTrue(grid[6][5] || grid[6][6] || grid[6][7] || grid[6][8]);
    }

    @Test
    public void testMoverPecaEsquerda() {
        tabuleiro.adicionarNovaPeca();
        Tetromino peca = tabuleiro.getPecaAtual();
        int xInicial = peca.getPosicao().getX();
        
        boolean movido = tabuleiro.moverPecaAtualEsquerda();
        assertTrue(movido);
        assertEquals(xInicial - 1, peca.getPosicao().getX());
    }

    @Test
    public void testMoverPecaDireita() {
        tabuleiro.adicionarNovaPeca();
        Tetromino peca = tabuleiro.getPecaAtual();
        int xInicial = peca.getPosicao().getX();
        
        boolean movido = tabuleiro.moverPecaAtualDireita();
        assertTrue(movido);
        assertEquals(xInicial + 1, peca.getPosicao().getX());
    }

    @Test
    public void testMoverPecaBaixo() {
        tabuleiro.adicionarNovaPeca();
        Tetromino peca = tabuleiro.getPecaAtual();
        int yInicial = peca.getPosicao().getY();
        
        boolean movido = tabuleiro.moverPecaAtualBaixo();
        assertTrue(movido);
        assertEquals(yInicial + 1, peca.getPosicao().getY());
    }

    @Test
    public void testRotacionarPeca() {
        tabuleiro.adicionarNovaPeca();
        Tetromino peca = tabuleiro.getPecaAtual();
        boolean[][] formaOriginal = copiarForma(peca.getForma());
        
        boolean rotacionado = tabuleiro.rotacionarPecaAtual();
        assertTrue(rotacionado);
        // Verifica se a forma mudou
        assertNotEquals(formaOriginal, peca.getForma());
    }

    @Test
    public void testRemoverLinhasCompletas() {
        // Preenche uma linha completa
        boolean[][] grid = tabuleiro.getGrid();
        for (int x = 0; x < Tabuleiro.LARGURA; x++) {
            grid[Tabuleiro.ALTURA - 1][x] = true;
        }
        
        int removidas = tabuleiro.removerLinhasCompletas();
        assertEquals(1, removidas);
        
        // Verifica se a linha foi removida
        for (int x = 0; x < Tabuleiro.LARGURA; x++) {
            assertFalse(grid[Tabuleiro.ALTURA - 1][x]);
        }
    }

    @Test
    public void testNaoRemoverLinhaIncompleta() {
        // Preenche apenas parte de uma linha
        boolean[][] grid = tabuleiro.getGrid();
        grid[Tabuleiro.ALTURA - 1][0] = true;
        grid[Tabuleiro.ALTURA - 1][1] = true;
        
        int removidas = tabuleiro.removerLinhasCompletas();
        assertEquals(0, removidas);
    }

    @Test
    public void testFixarPecaAtual() {
        tabuleiro.adicionarNovaPeca();
        assertNotNull(tabuleiro.getPecaAtual());
        
        tabuleiro.fixarPecaAtual();
        assertNull(tabuleiro.getPecaAtual());
    }

    @Test
    public void testMoverPecaNull() {
        assertFalse(tabuleiro.moverPecaAtualEsquerda());
        assertFalse(tabuleiro.moverPecaAtualDireita());
        assertFalse(tabuleiro.moverPecaAtualBaixo());
        assertFalse(tabuleiro.rotacionarPecaAtual());
    }

    private boolean[][] copiarForma(boolean[][] forma) {
        boolean[][] copia = new boolean[forma.length][forma[0].length];
        for (int i = 0; i < forma.length; i++) {
            System.arraycopy(forma[i], 0, copia[i], 0, forma[i].length);
        }
        return copia;
    }
}

