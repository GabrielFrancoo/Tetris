package main.Java.tetris.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Java.tetris.domain.SistemaPontuacao;

public class ScorePanel extends JPanel {
    private final JLabel lblPontuacao = new JLabel("Pontuação: 0");
    private final JLabel lblNivel = new JLabel("Nível: 1");

    public ScorePanel() {
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(200, 60));
        setBackground(Color.BLACK);
        lblPontuacao.setForeground(Color.WHITE);
        lblNivel.setForeground(Color.WHITE);
        lblPontuacao.setFont(new Font("Consolas", Font.BOLD, 16));
        lblNivel.setFont(new Font("Consolas", Font.BOLD, 16));
        add(lblPontuacao);
        add(lblNivel);
    }

    public void atualizar(SistemaPontuacao sistema) {
        lblPontuacao.setText("Pontuação: " + sistema.getPontos());
        lblNivel.setText("Nível: " + sistema.getNivel());
    }
}
