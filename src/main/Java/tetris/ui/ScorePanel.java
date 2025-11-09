package tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tetris.domain.SistemaPontuacao;

public class ScorePanel extends JPanel {
    private final JLabel lblPontuacao = new JLabel("0", SwingConstants.CENTER);
    private final JLabel lblNivel = new JLabel("1", SwingConstants.CENTER);
    private final JLabel lblPontuacaoTitulo = new JLabel("PONTUAÇÃO", SwingConstants.CENTER);
    private final JLabel lblNivelTitulo = new JLabel("NÍVEL", SwingConstants.CENTER);

    public ScorePanel() {
        setLayout(new GridLayout(2, 2, 20, 5));
        setPreferredSize(new Dimension(800, 90));
        setBackground(new Color(25, 25, 25));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Configuração dos títulos
        Font fontTitulo = new Font("Arial", Font.BOLD, 12);
        Color corTitulo = new Color(150, 150, 150);
        
        lblPontuacaoTitulo.setFont(fontTitulo);
        lblPontuacaoTitulo.setForeground(corTitulo);
        
        lblNivelTitulo.setFont(fontTitulo);
        lblNivelTitulo.setForeground(corTitulo);

        // Configuração dos valores
        Font fontValor = new Font("Consolas", Font.BOLD, 24);
        Color corValor = new Color(255, 200, 0);
        
        lblPontuacao.setFont(fontValor);
        lblPontuacao.setForeground(corValor);
        
        lblNivel.setFont(fontValor);
        lblNivel.setForeground(new Color(100, 200, 255));

        // Adiciona componentes
        add(lblPontuacaoTitulo);
        add(lblNivelTitulo);
        add(lblPontuacao);
        add(lblNivel);
    }

    public void atualizar(SistemaPontuacao sistema) {
        if (sistema != null) {
            lblPontuacao.setText(String.valueOf(sistema.getPontos()));
            lblNivel.setText(String.valueOf(sistema.getNivel()));
        }
    }
}

