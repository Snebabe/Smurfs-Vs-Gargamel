package com.group9.model.entities.defenders;

import com.group9.model.entities.attackers.AttackerType;

public class DefenceEntityFactory {

    public static DefenceEntity createDefender(DefenderType type) {
        switch (type) {
            case SHROOM:
                return new DefenceEntity(DefenderType.SHROOM, 200, 10, 5, type.getCost(), true);
            case BOXER:
                return new DefenceEntity(DefenderType.BOXER, 400, 20, 1, type.getCost(), false);
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
}
