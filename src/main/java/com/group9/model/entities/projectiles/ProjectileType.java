package com.group9.model.entities.projectiles;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.EntityType;
import com.group9.model.entities.characters.CharacterType;

/*public enum ProjectileType {
    ARROW(4),
    BULLET(6);

    private final int speed;

    ProjectileType(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

}*/

public class ProjectileType extends EntityType {

    private int speed;

    // Constructor to initialize name and speed, along with other properties
    public ProjectileType(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    // Getter for the speed
    public int getSpeed() {
        return speed;
    }
}