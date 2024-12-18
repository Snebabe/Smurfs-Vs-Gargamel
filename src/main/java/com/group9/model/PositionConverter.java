package com.group9.model;

import java.awt.*;

/*
 * Utility class to convert between game positions and grid coordinates.
 * Provides methods for mapping points to grid cells, and converting lane progress
 * to grid positions for attackers, projectiles, and defenders.
 */
public final class PositionConverter {

    // Maps a Point (pixel position) to a grid cell based on board dimensions and row/column count
    public static Point mapToGrid(Point point, int width, int height, int rowCount, int columnCount) {
        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        // Calculate clicked cell
        int column = point.x / cellWidth;
        int row = point.y / cellHeight;

        return new Point(row, column);
    }

    // Converts lane progress (a value between 0 and 1) to a corresponding cell index in the lane
    public static float attackerToCellIndex(double laneProgress, int columnCount) {
        return (float) ((1 - laneProgress) * columnCount);
    }

    // Converts lane progress (a value between 0 and 1) to a corresponding cell index in the lane for projectiles
    public static int projectileToCellIndex(double laneProgress, int columnCount) {
        return (int) (laneProgress * columnCount);
    }

    // Converts a cell index in the lane to the corresponding lane progress (0 to 1)
    public static float defenderToLaneProgress(int cellIndex, int columnCount) {
        return (float) cellIndex/columnCount;
    }

}