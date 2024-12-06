package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import com.group9.model.board.Board;
import com.group9.model.board.GridCell;
import com.group9.model.board.Lane;
import com.group9.model.entities.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackEntityFactory;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.defenders.DefenceEntityFactory;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.managers.AttackManager;
import com.group9.model.managers.GameStateManager;
import com.group9.model.managers.ResourceManager;
import com.group9.model.managers.WaveManager;

import java.util.*;

public class Model implements Observer {
    // Ha ett board
    // Tillgång till varje cell
    // getters som: getBoard

    private Board board;
    private WaveManager waveManager;
    private AttackManager attackManager;
    private GameStateManager gameStateManager;
    private int laneAmount = 7;
    private int laneSize = 9;
    private ResourceManager resourceManager;

    private List<DefenderType> defenderTypes;

    private int TICKS_PER_SECONDS;



    public Model(int TICKS_PER_SECONDS) {
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        this.board = new Board(laneAmount, laneSize, 100, TICKS_PER_SECONDS);
        this.waveManager = new WaveManager(new AttackEntityFactory(), board, TICKS_PER_SECONDS);
        this.attackManager = new AttackManager(board);
        this.gameStateManager = new GameStateManager(board);
        this.resourceManager = new ResourceManager();
        this.waveManager.addWaveCompleteListener(resourceManager);
        this.attackManager.addAttackDeathOberver(resourceManager);



        this.defenderTypes = new ArrayList<>();
        initializeDefenderTypes();
    }

    private void initializeDefenderTypes() {
        defenderTypes.add(new DefenderType("Shroom", "shroom.png", 100));
        defenderTypes.add(new DefenderType("Boxer", "sunflower.png", 150));
    }

    public List<DefenderType> getDefenderTypes() {
        return defenderTypes;
    }

    public void placeDefender(DefenderType defenderType, Position position) {

        if((resourceManager.getResources() >= defenderType.getCost()) && !isDefenderAt(position)) {
            setDefender(defenderType, position.getRow(), position.getCol());
            resourceManager.changeResources(-defenderType.getCost());
        }
        else if(isDefenderAt(position)) {
            System.out.println("Defender already in place");
        }
        else{
            System.out.println("Not enough money");
        }
    }

    public void update() {
        if (gameStateManager.isGameOver()) {
            resetGame();
        } // Reset game

        waveManager.update();
        board.getMoveManager().update();
    }

    public void resetGame() {
        this.board = new Board(laneAmount, laneSize, 100,TICKS_PER_SECONDS);
        waveManager.resetWaveManager(board);
        attackManager.resetBoard(board);
        gameStateManager.resetBoard(board);
        resourceManager.resetResources();
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }
    public AttackManager getAttackManager() {
        return attackManager;
    }

    public void startWave() {
        waveManager.startWave();
    }

    public int getLaneAmount() {
        return this.laneAmount;
    }

    public int getLaneSize() {
        return this.laneSize;
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

            // Create a snapshot to avoid ConcurrentModificationException
            List<Projectile> snapshot = new ArrayList<>(lane.getProjectiles());

            for (Projectile projectile : snapshot) {
                int col = (int) (projectile.getLaneProgress() * lane.getNumberOfCells());
                map.put(projectile, new Position(row, col));
            }
        }
        return map;
    }

    public void setDefender(DefenderType defender, int row, int col) {
        board.setDefender(new DefenceEntityFactory().createDefender(defender), row, col);
    }

    public boolean isDefenderAt(Position position) {
        return board.getLanes().get(position.getRow()).getDefenderAtIndex(position.getCol()) != null;
    }
}
