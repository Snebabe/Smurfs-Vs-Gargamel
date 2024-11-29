package com.group9.model;

public class DefenceEntityFactory {
    public static DefenceEntity createDefender(DefenderType type) {
        switch (type) {
            case SHROOM:
                return createShroom(); // Example: Replace with actual Shroom constructor
            case BOXER:
                return createBoxer(); // Example: Replace with actual Boxing constructor
            default:
                throw new IllegalArgumentException("Invalid defender type: " + type);
        }
    }
    public static DefenceShroom createShroom() {
        return new DefenceShroom(200, 10, 10 , 1, 100);
    }

    public DefenceBoxer createBoxer(Lane lane, int x) {
        return new DefenceBoxer(200, 75, 1, lane, x, 1, 150);
    }
}
