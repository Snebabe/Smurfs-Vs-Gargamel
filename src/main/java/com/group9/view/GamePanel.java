package com.group9.view;

import com.group9.model.Entity;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    Thread renderThread;

    public GamePanel(int WIDTH, int HEIGHT) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
    }

    public void startRendering() {
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void run() {
        while (renderThread != null) {
            System.out.println("Running!");

            // UPDATE (in our case fetch player position
            update();

            // DRAW updated information
            repaint();

        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        g2.fillRect(100, 100, 100, 100);
        g2.dispose();*/
        // Render the entities from the model
    }
}
