package com.group9.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JButton incrementButton;
    private JButton decrementButton;
    private JLabel countLabel;

    private GamePanel gamePanel;

    public View(int WIDTH, int HEIGHT) {
        // Set up the JFrame
        this.setTitle("Smurfs vs. Gargamel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new FlowLayout());

        // Initialize components
        gamePanel = new GamePanel(WIDTH, HEIGHT);
        gamePanel.startRendering();

        // Add components to the JFrame
        this.add(gamePanel);
        this.pack();

    }

    /*public void setCount(int count) {
        countLabel.setText("Count: " + count);
    }

    public void addIncrementListener(ActionListener listener) {
        incrementButton.addActionListener(listener);
    }

    public void addDecrementListener(ActionListener listener) {
        decrementButton.addActionListener(listener);
    }*/
}
