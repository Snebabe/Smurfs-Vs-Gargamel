package com.group9.view.services;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * The FontLoader class provides a method to load custom fonts from a specified path.
 */
public class FontLoader {

    /**
     * Loads a font from the specified path.
     *
     * @param fontPath the path to the font resource
     * @return the loaded Font object, or a fallback font if loading fails
     */
    public static Font loadFont(String fontPath) {
        try (InputStream is = FontLoader.class.getResourceAsStream(fontPath)) {
            if (is == null) {
                throw new IOException("Font resource not found: " + fontPath);
            }
            return Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Serif", Font.PLAIN, 25); // Fallback font
        }
    }
}