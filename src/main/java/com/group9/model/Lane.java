package com.group9.model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lane {
    // List to store attackers, sorted by their xPosition
    private List<AttackEntity> attackEntities;
    // List to store gridcells
    private List<GridCell> gridCells;
    private int cellSize;
    private int laneIndex;

    // Constructor
    public Lane(int laneSize, int cellSize) {
        this.attackEntities = new ArrayList<>();
        this.gridCells = new ArrayList<>();
        this.cellSize = cellSize;

        for (int cellIndex = 0; cellIndex < laneSize; cellIndex++) {
            this.gridCells.add(new GridCell(cellSize,laneIndex,cellIndex));
        }
    }

    // Add an Attacker to the lane
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
    }

    // Add a defender to the lane
    public boolean addDefender(DefenceEntity defenceEntity, int index) {
        if (defenceEntities.get(index)==null) {
            defenceEntity.setXPosition((cellSize/2)+cellSize*index);
            defenceEntities.add(index, defenceEntity);
            return true;
        }
        return false;
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public int getNumberOfCells(){
        return this.gridCells.length
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

    public void updateDefenders(){
        // animation (optional)
        // find closest enemy in lane
        // if enemy in range -> attack
        for(DefenceEntity defender: defenceEntities) {
            int x = defender.getXPosition();
            AttackEntity closest = getClosestAttacker(x);
            if (x - closest.getXPosition() <= defender.getRange()) {
                defender.useAttack(closest);
            }
        }
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
