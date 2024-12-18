package com.group9.model.entities.characters.defenders;

public enum DefenderType {
    SHROOM(100,"A durable defender with high health, the Shroom blocks enemies and absorbs damage, protecting other defenders."),
    BOXER(100, "A close-combat fighter who uses powerful punches to knock back and damage enemies up close."),
    ARCHER(150, "A skilled long-range attacker, the Archer strikes from a distance to deal damage before enemies get close."),
    SHOOTER(400, "A ruthless mercenary that has a perticular distaste for Gargamels...");

    private final int cost;

    private final String description;

    DefenderType(int cost, String description) {
        this.cost = cost;
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
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
