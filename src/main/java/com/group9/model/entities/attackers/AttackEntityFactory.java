package com.group9.model.entities.attackers;

import java.util.Random;

public class AttackEntityFactory {

    public AttackEntity createAttacker(AttackerType type) {
        switch (type) {
            case GARGAMEL:
                return new AttackEntity(AttackerType.GARGAMEL, 100, 10, 1, 1f, 1, 50) {
                };
            case FASTGARGAMEL:
                return new AttackEntity(AttackerType.FASTGARGAMEL, 100, 10, 1, 1f, 2, 50);
            default:
                throw new IllegalArgumentException("Invalid attacker type: " + type);
        }
    }
    public AttackEntity createRandomAttacker() {
        // Generate a random index
        int randomIndex = new Random().nextInt(AttackerType.values().length);

        // Get a random AttackerType
        AttackerType randomType = AttackerType.values()[randomIndex];

        // Create the attacker
        return createAttacker(randomType);
    }

}
