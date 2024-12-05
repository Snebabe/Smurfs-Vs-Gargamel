package com.group9.model.entities.defenders;

public class DefenceEntityFactory {
    public static DefenceEntity createDefender(DefenderType type) {
        switch (type.getName()) {
            case "Shroom":
                return new DefenceShroom(200, 3, 3, 1, type.getCost());
            case "Boxer":
                return new DefenceBoxer(1000, 75, 1, 1, type.getCost());
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
}
