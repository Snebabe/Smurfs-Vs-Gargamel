package com.group9.model.entities.defenders;

public enum DefenderType {
    SHROOM(100),
    BOXER(150);

    private final int cost;

    DefenderType(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}

/*
public class DefenderType {
    private String name;
    private String imagePath; // Path to the image file
    private int cost;

    public DefenderType(String name, String imagePath, int cost) {
        this.name = name;
        this.imagePath = imagePath;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getCost() {
        return cost;
    }
}*/
