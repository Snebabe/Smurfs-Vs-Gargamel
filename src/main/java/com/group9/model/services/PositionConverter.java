package com.group9.model.services;

/**
 * Utility class to convert between game positions and grid coordinates.
 * Provides methods for mapping points to grid cells, and converting lane progress
 * to grid positions for attackers, projectiles, and defenders.
 */
public final class PositionConverter {

    /**
     * Converts lane progress to a corresponding cell index in the lane for attackers.
     *
     * @param laneProgress the progress in the lane (0 to 1)
     * @param columnCount the number of columns in the lane
     * @return the corresponding cell index as a float
     */
    public static float attackerToCellIndex(double laneProgress, int columnCount) {
        return (float) ((1 - laneProgress) * columnCount);
    }

    /**
     * Converts lane progress to a corresponding cell index in the lane for projectiles.
     *
     * @param laneProgress the progress in the lane (0 to 1)
     * @param columnCount the number of columns in the lane
     * @return the corresponding cell index as an int
     */
    public static int projectileToCellIndex(double laneProgress, int columnCount) {
        return (int) (laneProgress * columnCount);
    }

    /**
     * Converts a cell index in the lane to the corresponding lane progress.
     *
     * @param cellIndex the index of the cell in the lane
     * @param columnCount the number of columns in the lane
     * @return the corresponding lane progress as a float
     */
    public static float defenderToLaneProgress(int cellIndex, int columnCount) {
        return (float) cellIndex/columnCount;
    }

}