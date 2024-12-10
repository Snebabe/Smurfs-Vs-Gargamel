package com.group9.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartPanel extends JPanel {
    private JButton startButton;
    private JLabel titleLabel;
    private Image backgroundImage;

    public StartPanel(ActionListener startButtonListener) {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/startPanelBg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add title label
        titleLabel = new JLabel("Smurfs vs. Gargamel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        startButton = new JButton("Start");
        startButton.addActionListener(startButtonListener);
        gbc.gridy = 1; // Set gridy to 1 for the button to place it below the title
        add(startButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}