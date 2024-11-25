package com.group9.model;

public class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position(int x, int y, int progress) {
        this.x = x + progress;
        this.y = y;
    }
}
