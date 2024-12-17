package com.group9.view.services;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
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