package com.group9.view.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameOverPanel extends JPanel {
    private JLabel gameOverLabel;
    private JLabel wavesCompletedLabel;
    private JButton newGameButton;
    private JButton quitButton;

    private Image backgroundImage;

    public GameOverPanel(int wavesCompleted, ActionListener newGameListener, ActionListener quitListener) {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/game_over_bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add "Game Over!" label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gameOverLabel = new JLabel("Village destroyed!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gameOverLabel.setForeground(Color.RED);
        add(gameOverLabel, gbc);

        // Add waves completed label
        gbc.gridy = 1;
        wavesCompletedLabel = new JLabel("Waves survived: " + wavesCompleted);
        wavesCompletedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        wavesCompletedLabel.setForeground(Color.RED);
        add(wavesCompletedLabel, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make button panel transparent to match background

        // Add new game button
        newGameButton = new JButton("Restart game");
        newGameButton.addActionListener(newGameListener);

        // Add quit button
        quitButton = new JButton("Exit game");
        quitButton.addActionListener(quitListener);

        buttonPanel.add(newGameButton);
        buttonPanel.add(quitButton);

        gbc.gridy = 2;
        add(buttonPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
