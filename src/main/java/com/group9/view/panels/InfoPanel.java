package com.group9.view.panels;

import com.group9.model.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class InfoPanel extends JPanel {
    private JPanel wavePanel;
    private JLabel waveLabel;
    private JLabel attackersLeftLabel;
    private JLabel resourcesLabel;
    private Model model;

    private Image backgroundImage;


    public InfoPanel(Model model, String backgroundImagePath) {
        this.model = model;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Game Info"));

        wavePanel = new JPanel();

        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource(backgroundImagePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to load background image.");
        }

        waveLabel = new JLabel("Current Wave: 0");
        attackersLeftLabel = new JLabel("Attackers Left: 0");
        resourcesLabel = new JLabel(" Resources: "+ model.getResourceManager().getResources());

        wavePanel.add(waveLabel);
        wavePanel.add(attackersLeftLabel);
        add(wavePanel, BorderLayout.NORTH);
        add(resourcesLabel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void update() {
        waveLabel.setText("Current Wave: " + model.getWaveManager().getWaveNumber());
        attackersLeftLabel.setText("Attackers Left: " + model.getWaveManager().getAttackersToSpawn());
        resourcesLabel.setText(" Resources: "+ model.getResourceManager().getResources());
    }
}