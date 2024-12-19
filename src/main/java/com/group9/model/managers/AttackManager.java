package com.group9.model.managers;

import com.group9.model.*;
import com.group9.model.Observer;
import com.group9.model.services.GameContext;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.services.PositionConverter;

import java.util.*;

/**
 * Manages the attack logic for the game, including handling attacks from both defenders and attackers.
 * Implements the Observer interface to update the state of the game.
 */
public class AttackManager implements Observer {
    private final Board board;
    private final List<AttackDeathObserver> attackDeathObservers;
    private final Map<Character, TickCounter> attackCounters;  // Map to track attack timing for characters
    private final int TICKS_PER_SECOND;

    /**
     * Constructs an AttackManager with the specified game board and ticks per second.
     *
     * @param board the game board
     * @param TICKS_PER_SECOND the number of ticks per second the game runs at
     */
    public AttackManager(Board board, int TICKS_PER_SECOND) {
        this.board = board;
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
        this.attackDeathObservers = new ArrayList<>();
        this.attackCounters = new HashMap<>();
        GameContext.setTicksPerSecond(TICKS_PER_SECOND);  // Set global ticks per second
    }


    /**
     * Handles attacks from defenders in the specified lane.
     *
     * @param lane the lane to handle defender attacks for
     */
    private void handleDefenderAttacks(Lane lane) {
        List<AttackEntity> attackers = lane.getAttackers();

        // Loop through all defenders in the lane
        for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
            DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);

            if (defender == null) continue;

            // Add a new counter for the defender if it doesn't exist
            if (!attackCounters.containsKey(defender)) {
                attackCounters.put(defender, new TickCounter(defender.getAttackDelay(),TICKS_PER_SECOND));
            }

            if (attackers.isEmpty()) {
                setAllDefendersToIDLE(lane);
                return;
            }

            // Attack if the defender is ready
            if (attackCounters.get(defender).getTicks() == 0) {
                if (defender.useAttack(lane, cellIndex)){
                    removeDeadAttackers(lane);
                    if (attackCounters.get(defender).getTickInterval()!=0) {
                        attackCounters.get(defender).increment();
                    }
                }
            } else if (attackCounters.get(defender).getTicks() >= attackCounters.get(defender).getTickInterval()) {
                attackCounters.get(defender).reset();
            } else {
                attackCounters.get(defender).increment();
            }

        }
    }

    /**
     * Removes dead attackers from the specified lane.
     *
     * @param lane the lane to remove dead attackers from
     */
    private void removeDeadAttackers(Lane lane){
        List<AttackEntity> attackers = lane.getAttackers();
        Iterator<AttackEntity> iterator = attackers.iterator();
        while (iterator.hasNext()) {
            AttackEntity target = iterator.next();
            if (target.isDead()) {
                iterator.remove();
                lane.removeAttacker(target);
                notifyAttackerDeath(target);
            }
        }
    }

    /**
     * Sets all defenders in the specified lane to the IDLE EntityState.
     *
     * @param lane the lane to set defenders to IDLE
     */
    private void setAllDefendersToIDLE(Lane lane) {
        for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
            DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);
            if (defender != null) {
                defender.setCurrentEntityState(EntityState.IDLE);
            }
        }
    }


    /**
     * Handles the attacks from attackers in the specified lane.
     *
     * @param lane the lane to handle melee attacks for
     */
    private void handleAttackerAttacks(Lane lane) {

        for (AttackEntity attacker : lane.getAttackers()) {
            int attackerCellIndex = (int) PositionConverter.attackerToCellIndex(attacker.getLaneProgress(), lane.getNumberOfCells());
            DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);

            if (defender != null) {
                attacker.setCurrentEntityState(EntityState.ATTACK);

                // Add a new counter for the attacker if it doesn't exist
                if (!attackCounters.containsKey(attacker)) {
                    attackCounters.put(attacker, new TickCounter(attacker.getAttackDelay(), TICKS_PER_SECOND));
                }
                // Attack if the attacker is ready
                if (attackCounters.get(attacker).getTicks() == 0) {
                    attacker.useAttack(defender);

                    if (defender.isDead()) {
                        attacker.setCurrentEntityState(EntityState.MOVE);
                        lane.setDefender(null, attackerCellIndex);
                    }
                    attackCounters.get(attacker).increment();
                } else if (attackCounters.get(attacker).getTicks() == attackCounters.get(attacker).getTickInterval()) {
                    attackCounters.get(attacker).reset();

                } else {
                    attackCounters.get(attacker).increment();
                }


            }
        }
    }

    public void addAttackDeathObserver(AttackDeathObserver observer) {
        attackDeathObservers.add(observer);
    }

    public void removeObserver(AttackDeathObserver observer) {
        attackDeathObservers.remove(observer);
    }

    public void notifyAttackerDeath(AttackEntity attacker) {
        for(AttackDeathObserver observer : attackDeathObservers) {
            observer.onAttackerDeath(attacker);
        }
    }

    @Override
    public void update() {
        for (Lane lane : board.getLanes()) {
            lane.sortAttackers();
            handleDefenderAttacks(lane);
            handleAttackerAttacks(lane);
        }
    }
}
