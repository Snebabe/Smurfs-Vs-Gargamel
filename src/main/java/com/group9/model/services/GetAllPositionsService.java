package com.group9.model.services;

import com.group9.model.Position;
import com.group9.model.board.Board;
import com.group9.model.board.GridCell;
import com.group9.model.board.Lane;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Provides methods to get the current positions of all attackers, defenders, and projectiles on the board.
 */
public class GetAllPositionsService {

    public static Map<AttackEntity, Position> getAllAttackersPosition(Board board) {
        Map<AttackEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (AttackEntity attacker : lane.getAttackers()) {
                int col = (int) PositionConverter.attackerToCellIndex(attacker.getLaneProgress(), lane.getNumberOfCells());
                map.put(attacker, new Position(row, col));
            }
        }
        return map;
    }

    public static Map<DefenceEntity, Position> getAllDefendersPosition(Board board) {
        Map<DefenceEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            // Iterate columns in reverse order
            for (int col = lane.getNumberOfCells() - 1; col >= 0; col--) {
                GridCell cell = lane.getGridCells().get(col);
                if (cell.hasDefender()) {
                    map.put(cell.getDefender(), new Position(row, col));
                }
            }
        }
        return map;

    }

    public static Map<Projectile, Position> getAllProjectilesPosition(Board board) {
        Map<Projectile, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            List<Projectile> snapshot = new ArrayList<>(lane.getProjectiles());
            for (Projectile projectile : snapshot) {
                int col = PositionConverter.projectileToCellIndex(projectile.getLaneProgress(), lane.getNumberOfCells());
                map.put(projectile, new Position(row, col));
            }
        }
        return map;
    }
}