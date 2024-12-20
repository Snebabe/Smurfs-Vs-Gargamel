package com.group9.model.services;


import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameContext class provides methods to retrieve targets for attacks
 * within a game, based on their position and range.
 */
public class GameContext {

    private static int TICKS_PER_SECOND;
    public static AttackEntity getSingleTarget(Lane lane, int range, int cellIndex) {
        List<AttackEntity> targetsInRange = getTargetsInRange(lane, range, cellIndex);
        if (targetsInRange.isEmpty()) {
            return null;
        }

        return targetsInRange.get(0);
    }

    public static List<AttackEntity> getTargetsInRange(Lane lane, int range, int cellIndex) {
        List<AttackEntity> targetsInRange = new ArrayList<>();
        for (AttackEntity attacker : lane.getAttackers()) {
            if (attackerInRange(attacker,lane,range,cellIndex)) {
                targetsInRange.add(attacker);
            }
        }
        return targetsInRange;
    }

    private static boolean attackerInRange(AttackEntity attackEntity, Lane lane, int range, int cellIndex) {
        float targetCellIndex = PositionConverter.attackerToCellIndex(attackEntity.getLaneProgress(), lane.getNumberOfCells());;
        float distance = targetCellIndex - cellIndex;
        return distance > 0 && distance <= range;
    }

    public static void setTicksPerSecond(int ticksPerSecond) {
        TICKS_PER_SECOND = ticksPerSecond;
    }

    public static int getTicksPerSecond() {
        return TICKS_PER_SECOND;
    }

}