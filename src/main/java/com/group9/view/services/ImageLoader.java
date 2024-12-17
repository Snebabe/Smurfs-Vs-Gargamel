package com.group9.view.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ImageLoader {
    public static Image loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image loadResizedImage(String path, int width, int height) {
        Image image = loadImage(path);
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImage;
    }
}
