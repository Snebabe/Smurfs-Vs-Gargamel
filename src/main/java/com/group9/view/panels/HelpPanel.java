package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel {
    private JButton backButton;
    private Image backgroundImage;

    public HelpPanel(ActionListener backButtonListener) {


        backgroundImage = ImageLoader.loadImage("/images/backgrounds/controlPanelBg.jpg");

        // Back button creation
        backButton = ImageButtonFactory.createImageButton("/images/buttons/backBtn.png", 200, 80);
        backButton.addActionListener(backButtonListener);

        // Layout for the main panel
        setLayout(new BorderLayout());

        // Instructions text area
        JTextArea instructions = new JTextArea();
        instructions.setText("""
                Welcome to Smurfs vs. Gargamel!
                
                Objective:
                The goal of the game is to protect the Smurf village from Gargamel's minions.
                You can place defenders on the board to prevent Gargamel´s minions from reaching the village.
                You can see your resources in the bottom right corner and how much each of the defenders cost.
                You can also see the current wave and how many attackers are left in the wave.
                
                Controls:
                To place a defender, click on a defender and then on an empty cell on the board.
                While hovering over the defenders you can see their stats.
                
                Tips:
                Place defenders wisely to optimize resources.
                
                Good luck!
                """);

        instructions.setEditable(false);
        instructions.setForeground(Color.WHITE);
        instructions.setLineWrap(true);
        instructions.setOpaque(false);
        instructions.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(instructions);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        scrollPane.setBorder(null); // Remove borders for cleaner appearance
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
