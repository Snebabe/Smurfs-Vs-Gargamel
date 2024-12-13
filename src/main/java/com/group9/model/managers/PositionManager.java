package com.group9.model.managers;

import com.group9.model.Position;
import com.group9.model.board.Board;
import com.group9.model.board.GridCell;
import com.group9.model.board.Lane;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionManager {
    private Board board;

    public PositionManager(Board board) {
        this.board = board;
    }

    public void resetBoard(Board board) {
        this.board = board;
    }

    public Map<AttackEntity, Position> getAllAttackersPosition() {
        Map<AttackEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (AttackEntity attacker : lane.getAttackers()) {
                int col = (int) ((1 - attacker.getLaneProgress()) * lane.getNumberOfCells());
                map.put(attacker, new Position(row, col));
            }
        }
        return map;
    }

    public Map<DefenceEntity, Position> getAllDefendersPosition() {
        Map<DefenceEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (int col = 0; col < lane.getNumberOfCells(); col++) {
                GridCell cell = lane.getGridCells().get(col);
                if (cell.hasDefender()) {
                    map.put(cell.getDefender(), new Position(row, col));
                }
            }
        }
        return map;
    }

    public Map<Projectile, Position> getAllProjectilesPosition() {
        Map<Projectile, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            List<Projectile> snapshot = new ArrayList<>(lane.getProjectiles());
            for (Projectile projectile : snapshot) {
                int col = (int) (projectile.getLaneProgress() * lane.getNumberOfCells());
                map.put(projectile, new Position(row, col));
            }
        }
        return map;
    }
}