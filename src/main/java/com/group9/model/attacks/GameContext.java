package com.group9.model.attacks;


import com.group9.model.PositionConverter;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;

import java.util.ArrayList;
import java.util.List;

public class GameContext {

    private static int TICKS_PER_SECOND;
    // Retrieve a specific target (for melee attacks)
    public static AttackEntity getSingleTarget(Lane lane, int range, int cellIndex) {
        List<AttackEntity> targetsInRange = getTargetsInRange(lane, range, cellIndex);
        // Check if the attackers list is empty
        if (targetsInRange.isEmpty()) {
            return null; // No attackers, so no target can be found
        }

        // Get the first attacker in the list
        return targetsInRange.get(0);
    }

    // Retrieve targets within range (for ranged attacks)
    public static List<AttackEntity> getTargetsInRange(Lane lane, int range, int cellIndex) {
        List<AttackEntity> targetsInRange = new ArrayList<>();
        for (AttackEntity attacker : lane.getAttackers()) {
            if (attackerInRange(attacker,lane,range,cellIndex)) {
                targetsInRange.add(attacker);
            }
        }
        return targetsInRange;
    }

    // Checks if a specific attacker is within range.
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