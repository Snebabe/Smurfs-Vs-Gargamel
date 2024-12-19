package com.group9.model.entities;

/**
 * Abstract class representing a type of entity.
 */
public abstract class EntityType {
    private final String name;

    /**
     * Constructs an EntityType with the specified name.
     *
     * @param name the name of the entity type
     */
    public EntityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
