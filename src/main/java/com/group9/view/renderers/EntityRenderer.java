package com.group9.view.renderers;

import com.group9.model.Model;
import com.group9.view.AnimationHandler;

import java.awt.*;

public interface EntityRenderer {
    void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth);
}
