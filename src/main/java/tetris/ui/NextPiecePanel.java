package tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tetris.domain.Tetromino;

public class NextPiecePanel extends JPanel {
    private static final int TAM_CELULA = 28;
    private static final int PREVIEW_SIZE = 4; // 4x4 grid para preview
    private Tetromino proximaPeca;
    private final JLabel lblTitulo;

    public NextPiecePanel() {
        setLayout(null);
        setPreferredSize(new Dimension(200, 200));
        setBackground(new Color(25, 25, 25));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Título removido (agora está no painel lateral)
        lblTitulo = new JLabel("", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(200, 200, 200));
        lblTitulo.setBounds(0, 0, 160, 20);
        add(lblTitulo);
    }

    public void atualizar(Tetromino proximaPeca) {
        this.proximaPeca = proximaPeca;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (proximaPeca == null) {
            return;
        }
        
        boolean[][] forma = proximaPeca.getForma();
        
        // Calcula os limites reais da peça (apenas blocos preenchidos)
        int minX = PREVIEW_SIZE, maxX = -1;
        int minY = PREVIEW_SIZE, maxY = -1;
        
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j]) {
                    minX = Math.min(minX, j);
                    maxX = Math.max(maxX, j);
                    minY = Math.min(minY, i);
                    maxY = Math.max(maxY, i);
                }
            }
        }
        
        // Se não encontrou blocos, não desenha
        if (maxX < 0 || maxY < 0) {
            return;
        }
        
        // Calcula dimensões reais da peça
        int larguraPeca = (maxX - minX + 1) * TAM_CELULA;
        int alturaPeca = (maxY - minY + 1) * TAM_CELULA;
        
        // Centraliza baseado no tamanho real da peça
        int offsetX = (getWidth() - larguraPeca) / 2;
        int offsetY = 30 + (PREVIEW_SIZE * TAM_CELULA - alturaPeca) / 2;
        
        // Desenha fundo do preview
        int previewX = (getWidth() - PREVIEW_SIZE * TAM_CELULA) / 2;
        int previewY = 30;
        g.setColor(new Color(15, 15, 15));
        g.fillRect(previewX, previewY, PREVIEW_SIZE * TAM_CELULA, PREVIEW_SIZE * TAM_CELULA);
        
        // Desenha a peça centralizada
        g.setColor(proximaPeca.getCor());
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j]) {
                    // Calcula posição relativa ao centro
                    int px = offsetX + (j - minX) * TAM_CELULA;
                    int py = offsetY + (i - minY) * TAM_CELULA;
                    
                    // Cor principal
                    g.setColor(proximaPeca.getCor());
                    g.fillRect(px, py, TAM_CELULA, TAM_CELULA);
                    
                    // Brilho interno
                    Color corBrilho = new Color(
                        Math.min(255, proximaPeca.getCor().getRed() + 40),
                        Math.min(255, proximaPeca.getCor().getGreen() + 40),
                        Math.min(255, proximaPeca.getCor().getBlue() + 40)
                    );
                    g.setColor(corBrilho);
                    g.fillRect(px + 1, py + 1, TAM_CELULA - 2, 6);
                    
                    // Borda
                    g.setColor(proximaPeca.getCor().darker().darker());
                    g.drawRect(px, py, TAM_CELULA, TAM_CELULA);
                }
            }
        }
        
        // Borda do preview
        g.setColor(new Color(60, 60, 60));
        g.drawRect(previewX, previewY, PREVIEW_SIZE * TAM_CELULA, PREVIEW_SIZE * TAM_CELULA);
    }
}

