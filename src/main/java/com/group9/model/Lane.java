package com.group9.model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lane {
    // List to store attackers, sorted by their xPosition
    private List<AttackEntity> attackEntities;
    // List to store gridcells
    private List<GridCell> gridCells;
    private int laneIndex;

    // Constructor
    public Lane(int laneSize, int laneIndex) {
        this.attackEntities = new ArrayList<>();
        this.gridCells = new ArrayList<>();
        this.laneIndex = laneIndex;

        for (int cellIndex = 0; cellIndex < laneSize; cellIndex++) {
            this.gridCells.add(new GridCell(laneIndex,cellIndex));
        }
    }

    // Add an Attacker to the lane
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
    }

    public AttackEntity getAttacker(int index) {
        return this.attackEntities.get(index);
    }
    public List<AttackEntity> getAttackers() {
        return this.attackEntities;
    }

    public DefenceEntity getDefender(int cellIndex) {
        return this.gridCells.get(cellIndex).getDefender();
    }

    public int getNumberOfCells(){
        return this.gridCells.size();
    }

    // Update all attackers in the lane (e.g., movement)

    public float getAttackerCellPosition(AttackEntity attacker) {
        return (1 - attacker.getLaneProgress()) * this.getNumberOfCells();
    }

    private boolean hasAttackerReachedDefender(AttackEntity attacker) {
        int attackerCellIndex = (int) this.getAttackerCellPosition(attacker);

        // Check if there's a defender at the same cell index
        return gridCells.get(attackerCellIndex).hasDefender();
    }

    public void updateDefenders(){
        // animation (optional)
        // find closest enemy in lane
        // if enemy in range -> attack
//        for(GridCell cell: gridCells) {
//            DefenceEntity defender = cell.getDefender();
//            int x = defender.getXPosition();
//            AttackEntity closest = getClosestAttacker(x);
//            if (x - closest.getXPosition() <= defender.getRange()) {
//                defender.useAttack(closest);
//            }
//        }
    }

    // Get the closest attacker to a specific position (e.g., for defenders)


    // Sort the attackers by lane progress using Insertion Sort
    public void sortAttackers() {
        for (int i = 1; i < attackEntities.size(); i++) {
            AttackEntity key = attackEntities.get(i);
            int j = i - 1;

            // Move attackers that are ahead of the key back by one position
            while (j >= 0 && attackEntities.get(j).getLaneProgress() > key.getLaneProgress()) {
                attackEntities.set(j + 1, attackEntities.get(j));
                j--;
            }
            attackEntities.set(j + 1, key);
        }
    }



    public void updateAttackers() {
        // Remove dead attackers
        attackEntities.removeIf(AttackEntity::isDead);

        for (AttackEntity attacker : attackEntities) {
            if (hasAttackerReachedDefender(attacker)) {
                attacker.move();
            }
        }
        // Sort again after movement
        sortAttackers();
    }

    public AttackEntity getClosestAttacker(int xPosition) {
        return attackEntities.stream()
                .filter(attacker -> attacker.getLaneProgress() > xPosition)
                .findFirst()
                .orElse(null);
    }

    // Add a defender to the lane
    public boolean addDefender(DefenderType defenderType, int index) {
        GridCell gridCell = gridCells.get(index);
        if (!gridCell.hasDefender()) {
            DefenceEntity defenceEntity = DefenceEntityFactory.createDefender(defenderType);
            gridCell.setDefender(defenceEntity);
            return true;
        }
        return false;
    }

    // For debugging: Print the state of attacker in the lane
    public void printAttacker() {
        System.out.println("Attacker in the lane:");
        for (AttackEntity attacker : attackEntities) {
            System.out.println("Attacker at position: " + attacker.getLaneProgress());
        }
    }
}
