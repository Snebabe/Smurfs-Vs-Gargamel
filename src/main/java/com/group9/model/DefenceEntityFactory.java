package com.group9.model;

public class DefenceEntityFactory {
    public static DefenceEntity createDefender(DefenderType type) {
        switch (type) {
            case SHROOM:
                return new DefenceShroom(200, 3, 3, 1, 100);
            case BOXER:
                return new DefenceBoxer(200, 75, 1, 1, 150);
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
}
