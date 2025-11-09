package tetris.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TipoTetrominoTest {

    @Test
    public void testCriarTodosOsTipos() {
        for (TipoTetromino tipo : TipoTetromino.values()) {
            Tetromino tetromino = tipo.criar();
            assertNotNull(tetromino);
            assertNotNull(tetromino.getForma());
            assertNotNull(tetromino.getCor());
        }
    }

    @Test
    public void testCriarI() {
        Tetromino t = TipoTetromino.I.criar();
        assertTrue(t instanceof TetrominoI);
    }

    @Test
    public void testCriarO() {
        Tetromino t = TipoTetromino.O.criar();
        assertTrue(t instanceof TetrominoO);
    }

    @Test
    public void testCriarT() {
        Tetromino t = TipoTetromino.T.criar();
        assertTrue(t instanceof TetrominoT);
    }

    @Test
    public void testCriarS() {
        Tetromino t = TipoTetromino.S.criar();
        assertTrue(t instanceof TetrominoS);
    }

    @Test
    public void testCriarZ() {
        Tetromino t = TipoTetromino.Z.criar();
        assertTrue(t instanceof TetrominoZ);
    }

    @Test
    public void testCriarJ() {
        Tetromino t = TipoTetromino.J.criar();
        assertTrue(t instanceof TetrominoJ);
    }

    @Test
    public void testCriarL() {
        Tetromino t = TipoTetromino.L.criar();
        assertTrue(t instanceof TetrominoL);
    }

    @Test
    public void testAleatorio() {
        // Testa várias vezes para garantir aleatoriedade
        TipoTetromino primeiro = TipoTetromino.aleatorio();
        assertNotNull(primeiro);
        
        // Testa que o método sempre retorna um valor válido
        for (int i = 0; i < 20; i++) {
            TipoTetromino atual = TipoTetromino.aleatorio();
            assertNotNull(atual);
            // Verifica que é um dos valores válidos do enum
            boolean encontrado = false;
            for (TipoTetromino tipo : TipoTetromino.values()) {
                if (tipo.equals(atual)) {
                    encontrado = true;
                    break;
                }
            }
            assertTrue("Tipo retornado deve ser um valor válido do enum", encontrado);
        }
    }

    @Test
    public void testValoresEnum() {
        TipoTetromino[] valores = TipoTetromino.values();
        assertEquals(7, valores.length); // I, O, T, S, Z, J, L
    }
}

