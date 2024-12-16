package com.group9.model.board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.movement.Movable;

import java.util.ArrayList;
import java.util.List;

public class Lane {
    // List to store attackers, sorted by their xPosition
    private List<AttackEntity> attackEntities;
    // List to store gridcells
    private List<GridCell> gridCells;

    private List<Projectile> projectiles;

    private List<Movable> movables;

    private int laneIndex;

    public Lane(int laneSize, int laneIndex) {
        this.attackEntities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.movables = new ArrayList<>();

        // Generate grid cells
        this.laneIndex = laneIndex;
        this.gridCells = new ArrayList<>();
        for (int cellIndex = 0; cellIndex < laneSize; cellIndex++) {
            this.gridCells.add(new GridCell(laneIndex,cellIndex));
        }
    }

    public List<Projectile> getProjectiles() {
        return this.projectiles;
    }
    public List<GridCell> getGridCells() {
        return gridCells;
    }

    public List<AttackEntity> getAttackers() {
        return this.attackEntities;
    }
    public List<Movable> getMovables() {
        return this.movables;
    }

    // Add an Attacker to the lane
    public void addAttacker(AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
        movables.add(attackEntity);
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
        movables.add(projectile);
    }

    public void removeAttacker(AttackEntity attackEntity) {
        attackEntities.remove(attackEntity);
        movables.remove(attackEntity);
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
        movables.remove(projectile);
    }

    // Add a defender to the lane
    public void setDefender(DefenceEntity defender, int col) {
        GridCell cell = this.gridCells.get(col);
        cell.setDefender(defender);
    }

    public int getNumberOfCells(){
        return this.gridCells.size();
    }

    public DefenceEntity getDefenderAtIndex(int index) {
        GridCell gridcell = gridCells.get(index);
        if (gridcell != null) {
            return gridCells.get(index).getDefender();
        }
        return null;
    }

    // Sort the attackers by lane progress using Insertion Sort
    public void sortAttackers() {
        for (int i = 1; i < attackEntities.size(); i++) {
            AttackEntity key = attackEntities.get(i);
            int j = i - 1;

            // Move attackers that are ahead of the key back by one position
            while (j >= 0 && attackEntities.get(j).getLaneProgress() < key.getLaneProgress()) {
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
            System.out.println("Attacker at position: " + attacker.getLaneProgress());
        }
    }


}
