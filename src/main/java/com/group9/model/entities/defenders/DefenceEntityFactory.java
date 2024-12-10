package com.group9.model.entities.defenders;

import com.group9.model.entities.attackers.AttackerType;

public class DefenceEntityFactory {

    public static DefenceEntity createDefender(DefenderType type) {
        switch (type) {
            case SHROOM:
                return new DefenceEntity(DefenderType.SHROOM, 200, 0, 0, type.getCost(), false);
            case BOXER:
                return new DefenceEntity(DefenderType.BOXER, 100, 15, 1, type.getCost(), false);
            case ARCHER:
                return new DefenceEntity(DefenderType.ARCHER, 50, 20, 5, type.getCost(), true);
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
}
