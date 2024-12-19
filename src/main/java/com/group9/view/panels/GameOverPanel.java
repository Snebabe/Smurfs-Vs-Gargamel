package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private JLabel gameOverLabel;
    private JLabel wavesCompletedLabel;
    private JButton newGameButton;
    private JButton quitButton;
    private Image backgroundImage;

    public GameOverPanel(int wavesCompleted, ActionListener newGameListener, ActionListener quitListener, Font font) {
        backgroundImage = ImageLoader.loadImage("/images/backgrounds/gameOverBg.png");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add "Game Over!" label
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add waves completed label
        gbc.gridy = 0;
        wavesCompletedLabel = new JLabel("Waves survived: " + wavesCompleted);
        wavesCompletedLabel.setFont(getFont().deriveFont(Font.BOLD, 36));
        wavesCompletedLabel.setForeground(Color.CYAN);
        add(wavesCompletedLabel, gbc);

        // Create a panel for the buttons that is transparent to make background visible
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        // Add new game button
        newGameButton = ImageButtonFactory.createImageButton("/images/buttons/restartBtn.png", 100, 50);
        newGameButton.addActionListener(newGameListener);

        // Add quit button
        quitButton = ImageButtonFactory.createImageButton("/images/buttons/exitBtn.png", 100, 50);
        quitButton.addActionListener(quitListener);

        buttonPanel.add(newGameButton);
        buttonPanel.add(quitButton);

        // Position the button panel below the wave completion label
        gbc.gridy = 1;
        add(buttonPanel, gbc);
    }

    // Custom painting of the background image for the Game Over panel
    // This is made through paintComponent so that the background is rendered first and under everything else
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, stretched to fill the entire panel
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
