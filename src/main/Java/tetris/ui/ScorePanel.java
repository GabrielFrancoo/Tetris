package tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tetris.domain.SistemaPontuacao;

public class ScorePanel extends JPanel {
    private final JLabel lblPontuacao = new JLabel("Pontuação: 0");
    private final JLabel lblNivel = new JLabel("Nível: 1");

    public ScorePanel() {
        // Barra inferior centralizada
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        // Altura fixa para a barra inferior; a largura será ajustada pela janela
        setPreferredSize(new Dimension(700, 70));
        setBackground(Color.BLACK);

        lblPontuacao.setForeground(Color.WHITE);
        lblNivel.setForeground(Color.WHITE);

        lblPontuacao.setFont(new Font("Consolas", Font.BOLD, 18));
        lblNivel.setFont(new Font("Consolas", Font.BOLD, 18));

        add(lblPontuacao);
        add(lblNivel);
    }

    public void atualizar(SistemaPontuacao sistema) {
        if (sistema != null) {
            lblPontuacao.setText("Pontuação: " + sistema.getPontos());
            lblNivel.setText("Nível: " + sistema.getNivel());
        }
    }
}

