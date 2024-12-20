package com.group9.view.renderers;

import com.group9.model.Model;
import com.group9.view.AnimationHandler;
import java.awt.*;
/**
 * EntityRenderer defines a common interface for rendering game entities.
 * Implementations of this interface will provide the specific rendering logic for different types of entities.
 */
public interface EntityRenderer {

    /**
     * Draws the game entity on the game grid
     *
     * @param g2d the Graphics2D context to draw on
     * @param model the game model containing the state of the game
     * @param animationHandler the handler for managing animations
     * @param cellWidth the width of each cell in the grid
     * @param cellHeight the height of each cell in the grid
     * @param panelWidth the width of the panel
     */
    void draw(Graphics2D g2d, Model model, AnimationHandler animationHandler, int cellWidth, int cellHeight, int panelWidth);
}
