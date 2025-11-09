package tetris.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TetrominoTest {

    private Tetromino tetromino;

    @Before
    public void setUp() {
        tetromino = new TetrominoI();
    }

    @Test
    public void testInicializacao() {
        assertNotNull(tetromino.getForma());
        assertNotNull(tetromino.getCor());
        assertNotNull(tetromino.getPosicao());
    }

    @Test
    public void testRotacionar() {
        boolean[][] formaOriginal = copiarForma(tetromino.getForma());
        tetromino.rotacionar();
        boolean[][] formaRotacionada = tetromino.getForma();
        
        // Verifica se a forma mudou
        assertNotEquals(formaOriginal, formaRotacionada);
    }

    @Test
    public void testRotacionarReverso() {
        boolean[][] formaOriginal = copiarForma(tetromino.getForma());
        tetromino.rotacionar();
        tetromino.rotacionarReverso();
        
        // Deve voltar à forma original
        assertArrayEquals(formaOriginal, tetromino.getForma());
    }

    @Test
    public void testMoverBaixo() {
        Posicao posicaoInicial = tetromino.getPosicao();
        tetromino.moverBaixo();
        assertEquals(posicaoInicial.getX(), tetromino.getPosicao().getX());
        assertEquals(posicaoInicial.getY() + 1, tetromino.getPosicao().getY());
    }

    @Test
    public void testMoverEsquerda() {
        Posicao posicaoInicial = tetromino.getPosicao();
        tetromino.moverEsquerda();
        assertEquals(posicaoInicial.getX() - 1, tetromino.getPosicao().getX());
        assertEquals(posicaoInicial.getY(), tetromino.getPosicao().getY());
    }

    @Test
    public void testMoverDireita() {
        Posicao posicaoInicial = tetromino.getPosicao();
        tetromino.moverDireita();
        assertEquals(posicaoInicial.getX() + 1, tetromino.getPosicao().getX());
        assertEquals(posicaoInicial.getY(), tetromino.getPosicao().getY());
    }

    @Test
    public void testSetPosicao() {
        Posicao novaPosicao = new Posicao(10, 20);
        tetromino.setPosicao(novaPosicao);
        assertEquals(10, tetromino.getPosicao().getX());
        assertEquals(20, tetromino.getPosicao().getY());
    }

    @Test
    public void testTetrominoONaoRotaciona() {
        Tetromino tetrominoO = new TetrominoO();
        boolean[][] formaOriginal = copiarForma(tetrominoO.getForma());
        
        tetrominoO.rotacionar();
        assertArrayEquals(formaOriginal, tetrominoO.getForma());
        
        tetrominoO.rotacionarReverso();
        assertArrayEquals(formaOriginal, tetrominoO.getForma());
    }

    @Test
    public void testRotacaoCompleta() {
        boolean[][] formaOriginal = copiarForma(tetromino.getForma());
        
        // 4 rotações devem voltar à forma original
        tetromino.rotacionar();
        tetromino.rotacionar();
        tetromino.rotacionar();
        tetromino.rotacionar();
        
        assertArrayEquals(formaOriginal, tetromino.getForma());
    }

    private boolean[][] copiarForma(boolean[][] forma) {
        boolean[][] copia = new boolean[forma.length][forma[0].length];
        for (int i = 0; i < forma.length; i++) {
            System.arraycopy(forma[i], 0, copia[i], 0, forma[i].length);
        }
        return copia;
    }
}

