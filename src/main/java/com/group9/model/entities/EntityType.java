package com.group9.model.entities;

public abstract class EntityType {
    private String name;

    public EntityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
