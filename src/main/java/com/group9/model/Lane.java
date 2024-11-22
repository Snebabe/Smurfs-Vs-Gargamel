package com.group9.model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lane {
    // List to store attackers, sorted by their xPosition
    private List<AttackEntity> attackEntities;
    // List to store defenders
    private List<DefenceEntity> defenceEntities;
    private int celldimension;

    // Constructor
    public Lane(int celldimension) {
        this.attackEntities = new ArrayList<>();
        this.defenceEntities = new ArrayList<>();
        this.celldimension = celldimension;
    }

    // Add an Attacker to the lane
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
    }

    // Add a defender to the lane
    public boolean addDefender(DefenceEntity defenceEntity, int index) {
        if (defenceEntities.get(index)==null) {
            defenceEntity.setXPosition((celldimension/2)+celldimension*index);
            defenceEntities.add(index, defenceEntity);
            return true;
        }
        return false;
    }

    // Update all attackers in the lane (e.g., movement)
    public void updateAttackers() {
        for (AttackEntity attacker : attackEntities) {
            if (!attacker.hasReachedTarget()) {
                attacker.move();
            }
        }
        // Sort again after movement
        insertionSort();
        // Remove dead attackers
        attackEntities.removeIf(AttackEntity::isDead);
    }

    // Get the closest attacker to a specific position (e.g., for defenders)
    public AttackEntity getClosestAttacker(int xPosition) {
        return attackEntities.stream()
                .filter(attacker -> attacker.getXPosition() > xPosition)
                .findFirst()
                .orElse(null);
    }

    // Sort the attackers by xPosition using Insertion Sort
    private void insertionSort() {
        for (int i = 1; i < attackEntities.size(); i++) {
            AttackEntity key = attackEntities.get(i);
            int j = i - 1;

            // Move attackers that are ahead of the key back by one position
            while (j >= 0 && attackEntities.get(j).getXPosition() > key.getXPosition()) {
                attackEntities.set(j + 1, attackEntities.get(j));
                j--;
            }
            attackEntities.set(j + 1, key);
        }
    }

    // For debugging: Print the state of attacker in the lane
    public void printAttacker() {
        System.out.println("Attacker in the lane:");
        for (AttackEntity attacker : attackEntities) {
            System.out.println("Attacker at position: " + attacker.getXPosition());
        }
    }
}
