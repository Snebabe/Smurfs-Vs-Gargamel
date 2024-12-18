package com.group9.model;

import java.awt.*;

public final class PositionConverter {
    public static Point mapToGrid(Point point, int width, int height, int rowCount, int columnCount) {
        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        // Calculate clicked cell
        int column = point.x / cellWidth;
        int row = point.y / cellHeight;

        return new Point(row, column);
    }

    public static float attackerToCellIndex(double laneProgress, int columnCount) {
        return (float) ((1 - laneProgress) * columnCount);
    }

    public static int projectileToCellIndex(double laneProgress, int columnCount) {
        return (int) (laneProgress * columnCount);
    }

    public static float defenderToLaneProgress(int cellIndex, int columnCount) {
        return (float) cellIndex/columnCount;
    }


}