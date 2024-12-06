package com.group9.view.panels;

import com.group9.model.Model;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JPanel wavePanel;
    private JLabel waveLabel;
    private JLabel attackersLeftLabel;
    private JLabel resourcesLabel;
    private Model model;

    public InfoPanel(Model model) {
        this.model = model;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Game Info"));

        wavePanel = new JPanel();


        waveLabel = new JLabel("Current Wave: 0");
        attackersLeftLabel = new JLabel("Attackers Left: 0");
        resourcesLabel = new JLabel(" Resources: "+ model.getResourceManager().getResources());

        wavePanel.add(waveLabel);
        wavePanel.add(attackersLeftLabel);
        add(wavePanel, BorderLayout.NORTH);
        add(resourcesLabel, BorderLayout.SOUTH);
    }

    public void update() {
        waveLabel.setText("Current Wave: " + model.getWaveManager().getWaveNumber());
        attackersLeftLabel.setText("Attackers Left: " + model.getWaveManager().getAttackersToSpawn());
        resourcesLabel.setText(" Resources: "+ model.getResourceManager().getResources());
    }
}