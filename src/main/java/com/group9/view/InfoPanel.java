package com.group9.view;

import com.group9.model.Model;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel waveLabel;
    private JLabel attackersLeftLabel;
    private Model model;

    public InfoPanel(Model model) {
        this.model = model;
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createTitledBorder("Game Info"));

        waveLabel = new JLabel("Current Wave: 0");
        attackersLeftLabel = new JLabel("Attackers Left: 0");

        add(waveLabel);
        add(attackersLeftLabel);
    }

    public void update() {
        waveLabel.setText("Current Wave: " + model.getWaveManager().getWaveNumber());
        attackersLeftLabel.setText("Attackers Left: " + model.getWaveManager().getAttackersToSpawn());
    }
}