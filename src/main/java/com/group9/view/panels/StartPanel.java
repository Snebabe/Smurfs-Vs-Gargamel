package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private JButton startButton;
    private JButton helpButton;
    private Image backgroundImage;

    public StartPanel(ActionListener startButtonListener, ActionListener helpButtonListener) {

        this.backgroundImage = ImageLoader.loadImage("/images/backgrounds/startPanelBg.png");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        startButton = ImageButtonFactory.createImageButton("/images/buttons/playBtn.png", 200, 80);
        startButton.addActionListener(startButtonListener);
        buttonPanel.add(startButton);

        helpButton = ImageButtonFactory.createImageButton("/images/buttons/helpBtn.png", 200, 80);
        helpButton.addActionListener(helpButtonListener);
        buttonPanel.add(helpButton);

        gbc.gridy = 1;
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