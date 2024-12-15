package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.entities.defenders.DefenderType;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefenderPanel extends JPanel {
    public DefenderPanel(List<InputObserver> observers) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        TitledBorder borderTitle = BorderFactory.createTitledBorder("Defenders");
        borderTitle.setTitleColor(Color.white);
        setBorder(borderTitle);
        setOpaque(false);
        String path = "";

        int ImageButtonWidth = 64;
        int ImageButtonHeight = 64;

        for (DefenderType type : DefenderType.values()) {


            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


            JLabel costLabel = new JLabel("Cost: " + type.getCost());
            costLabel.setForeground(Color.WHITE);
            costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            try {
                // Load the image
                Image buttonIcon = ImageIO.read(new File(getClass().getResource("/images/defenders/" + type.toString().toLowerCase() + "/idle/0.png").toURI()));

                //Scaling the image to desired size
                Image scaledIcon = buttonIcon.getScaledInstance(ImageButtonWidth, ImageButtonHeight, Image.SCALE_SMOOTH);

                // Create an ImageIcon with the scaled image
                JButton button = new JButton(new ImageIcon(scaledIcon));
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.setToolTipText(type.getDescription());
                button.addActionListener(e -> {
                    for (InputObserver observer : observers) {
                        observer.onDefenderSelected(type);
                    }
                });

                // Add a label for the defender type under the image
                JLabel nameLabel = new JLabel(type.toString());
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                nameLabel.setForeground(Color.WHITE);

                panel.add(costLabel);  // Add cost label
                panel.add(button);     // Add image button
                panel.add(nameLabel);  // Add name label
                add(panel);

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                System.err.println("Failed to load DefenderPanel at: " + path);
            }
        }
    }
}
