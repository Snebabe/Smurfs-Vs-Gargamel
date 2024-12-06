package com.group9.model.entities.defenders;

public class DefenceEntityFactory {



    public static DefenceEntity createDefender(DefenderType type) {
        switch (type.getName()) {
            case "Shroom":
                return new DefenceShroom(200, 10, 5, 1, type.getCost(), true);
            case "Boxer":
                return new DefenceBoxer(400, 20, 1, 1, type.getCost(), false);
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
}
