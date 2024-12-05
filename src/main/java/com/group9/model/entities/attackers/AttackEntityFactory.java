package com.group9.model.entities.attackers;

public class AttackEntityFactory {

    public AttackEntity createAttacker(String type) {
        switch (type) {
            case "Gargamel":
                return new AttackGargamel(100, 10, 1, 1);
            case "FastGargamel":
                return new AttackGargamel(100, 10, 1, 2);
            default:
                throw new IllegalArgumentException("Invalid attacker type: " + type);
        }
    }
    public AttackEntity createRandomAttacker() {
        String[] attackerTypes = {"Gargamel", "FastGargamel"};
        String randomType = attackerTypes[(int) (Math.random() * attackerTypes.length)];
        return createAttacker(randomType);
    }

}
