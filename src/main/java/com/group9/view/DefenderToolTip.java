package com.group9.view;
import com.group9.model.entities.characters.defenders.DefenderType;

import javax.swing.*;
import java.awt.*;

public class DefenderToolTip extends JToolTip {
    private final JPanel panel;

    public DefenderToolTip(DefenderType type) {
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(createLabel("Health: " + type.getMaxHealth()));
        panel.add(createLabel("Damage: " + type.getAttackDamage()));
        panel.add(createLabel("Range: " + type.getRange()));
        panel.add(createLabel("Attack Speed: " + type.getAttackSpeed()));

        add(panel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return label;
    }
}