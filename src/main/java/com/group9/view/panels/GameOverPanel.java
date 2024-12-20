package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The GameOverPanel class represents the panel displayed when the game is over.
 * It shows the number of waves completed and provides buttons to start a new game or quit.
 */
public class GameOverPanel extends JPanel {
    private JLabel gameOverLabel;
    private JLabel wavesCompletedLabel;
    private JButton newGameButton;
    private JButton quitButton;
    private Image backgroundImage;

    /**
     * Constructs a GameOverPanel with the specified number of waves completed.
     *
     * @param wavesCompleted the number of waves completed
     * @param newGameListener the action listener for the new game button
     */
    public GameOverPanel(int wavesCompleted, List<InputObserver> inputObservers, ActionListener newGameListener) {
        backgroundImage = ImageLoader.loadImage("/images/backgrounds/gameOverBg.png");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        addWavesCompletedLabel(wavesCompleted, gbc);

        // Add start game button and quit button, and set their action listeners
        addButtons(newGameListener, inputObservers, gbc);
    }

    private void addWavesCompletedLabel(int wavesCompleted, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;

        wavesCompletedLabel = new JLabel("Waves survived: " + wavesCompleted);
        wavesCompletedLabel.setFont(getFont().deriveFont(Font.BOLD, 36));
        wavesCompletedLabel.setForeground(Color.CYAN);
        add(wavesCompletedLabel, gbc);
    }

    private void addButtons(ActionListener newGameListener, List<InputObserver> inputObservers, GridBagConstraints gbc) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        newGameButton = ImageButtonFactory.createImageButton("/images/buttons/restartBtn.png", 100, 50);
        newGameButton.addActionListener(newGameListener);

        quitButton = ImageButtonFactory.createImageButton("/images/buttons/exitBtn.png", 100, 50);
        quitButton.addActionListener(quitListener);

        buttonPanel.add(newGameButton);
        buttonPanel.add(quitButton);

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