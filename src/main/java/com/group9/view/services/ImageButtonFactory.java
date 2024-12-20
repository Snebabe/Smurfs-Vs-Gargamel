package com.group9.view.services;

import javax.swing.*;
import java.awt.*;
/**
 * The ImageButtonFactory class provides a method to create image buttons with specified dimensions.
 */
public class ImageButtonFactory {

    public static JButton createImageButton(String imagePath, int width, int height) {
        Image image = ImageLoader.loadResizedImage(imagePath, width, height);
        ImageIcon icon = new ImageIcon(image);
        JButton button = new JButton(icon);

        button.setPreferredSize(new Dimension(width, height));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
    }
}
