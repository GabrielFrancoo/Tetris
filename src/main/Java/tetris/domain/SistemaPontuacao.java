package tetris.domain;

public class SistemaPontuacao {
    private int pontos = 0;
    private int nivel = 1;

    public void adicionarLinhas(int linhas) {
        if (linhas < 0 || linhas > 4) {
            return; // Validação
        }
        
        int ganho = switch (linhas) {
            case 1 -> 40 * nivel;
            case 2 -> 100 * nivel;
            case 3 -> 300 * nivel;
            case 4 -> 1200 * nivel;
            default -> 0;
        };
        pontos += ganho;
        // Sobe de nível a cada 500 pontos ao invés de 1000
        if (pontos / 500 > nivel - 1) nivel++;
    }

    public int getPontos() { 
        return pontos; 
    }
    
    public int getNivel() { 
        return nivel; 
    }
}

