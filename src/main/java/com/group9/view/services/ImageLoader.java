package com.group9.view.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The ImageLoader class provides methods to load and resize images from specified filepaths.
 */
public class ImageLoader {

    /**
     * Loads an image from the specified path.
     *
     * @param path the path to the image resource
     * @return the loaded Image object, or null if loading fails
     */
    public static Image loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads an image from the specified path and resizes it to the given dimensions.
     *
     * @param path the path to the image resource
     * @param width the desired width of the resized image
     * @param height the desired height of the resized image
     * @return the resized Image object, or null if the image could not be loaded
     */
    public static Image loadResizedImage(String path, int width, int height) {
        Image image = loadImage(path);
        if(image == null) {
            return null;
        }
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImage;
    }
}
