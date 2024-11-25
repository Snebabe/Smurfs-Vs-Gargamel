package com.group9.model;

public class DefenceEntityFactory {
    public DefenceShroom createShroom(Lane lane, int x) {
        return new DefenceShroom(200, 10, 10, lane , x, 1, 100);
    }

    public DefenceBoxer createBoxer(Lane lane, int x) {
        return new DefenceBoxer(200, 75, 1, lane, x, 1, 150);
    }
}
