package tetris.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOverDialog extends JDialog {
    private boolean reiniciar = false;

    public GameOverDialog(javax.swing.JFrame parent, int pontuacao, int nivel) {
        super(parent, true); // Modal
        setTitle("Game Over");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(20, 20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Título Game Over
        JLabel lblGameOver = new JLabel("GAME OVER", SwingConstants.CENTER);
        lblGameOver.setFont(new Font("Arial", Font.BOLD, 36));
        lblGameOver.setForeground(new Color(255, 50, 50));
        painelPrincipal.add(lblGameOver, BorderLayout.NORTH);
        
        // Painel de informações
        JPanel painelInfo = new JPanel(new GridBagLayout());
        painelInfo.setBackground(new Color(20, 20, 20));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Pontuação
        JLabel lblPontuacaoTitulo = new JLabel("Pontuação Final:", SwingConstants.RIGHT);
        lblPontuacaoTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblPontuacaoTitulo.setForeground(new Color(200, 200, 200));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        painelInfo.add(lblPontuacaoTitulo, gbc);
        
        JLabel lblPontuacao = new JLabel(String.valueOf(pontuacao), SwingConstants.LEFT);
        lblPontuacao.setFont(new Font("Consolas", Font.BOLD, 24));
        lblPontuacao.setForeground(new Color(255, 200, 0));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelInfo.add(lblPontuacao, gbc);
        
        // Nível
        JLabel lblNivelTitulo = new JLabel("Nível Alcançado:", SwingConstants.RIGHT);
        lblNivelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblNivelTitulo.setForeground(new Color(200, 200, 200));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        painelInfo.add(lblNivelTitulo, gbc);
        
        JLabel lblNivel = new JLabel(String.valueOf(nivel), SwingConstants.LEFT);
        lblNivel.setFont(new Font("Consolas", Font.BOLD, 24));
        lblNivel.setForeground(new Color(100, 200, 255));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelInfo.add(lblNivel, gbc);
        
        painelPrincipal.add(painelInfo, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        painelBotoes.setBackground(new Color(20, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFechar.setPreferredSize(new Dimension(120, 35));
        btnFechar.setBackground(new Color(60, 60, 60));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.setBorder(BorderFactory.createRaisedBevelBorder());
        btnFechar.addActionListener(e -> {
            reiniciar = false;
            dispose();
        });
        
        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReiniciar.setPreferredSize(new Dimension(120, 35));
        btnReiniciar.setBackground(new Color(50, 150, 50));
        btnReiniciar.setForeground(Color.WHITE);
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.setBorder(BorderFactory.createRaisedBevelBorder());
        btnReiniciar.addActionListener(e -> {
            reiniciar = true;
            dispose();
        });
        
        painelBotoes.add(btnReiniciar);
        painelBotoes.add(btnFechar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        add(painelPrincipal);
        pack();
        setLocationRelativeTo(parent);
    }
    
    public boolean isReiniciar() {
        return reiniciar;
    }
}

