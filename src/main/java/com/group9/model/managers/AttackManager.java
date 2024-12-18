package com.group9.model.managers;

import com.group9.model.*;
import com.group9.model.Observer;
import com.group9.model.attacks.GameContext;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;

import java.util.*;

public class AttackManager implements Observer {
    private Board board;
    private List<AttackDeathObserver> attackDeathObservers;
    private Map<Character, TickCounter> attackCounters;
    private int TICKS_PER_SECOND;

    public AttackManager(Board board, int TICKS_PER_SECOND) {
        this.board = board;
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
        this.attackDeathObservers = new ArrayList<>();
        this.attackCounters = new HashMap<>();
        GameContext.setTicksPerSecond(TICKS_PER_SECOND);
    }

    public void addAttackDeathOberver(AttackDeathObserver observer) {
        attackDeathObservers.add(observer);
    }

    public void removeObserver(AttackDeathObserver observer) {
        attackDeathObservers.remove(observer);
    }

    public void notifyAttackerDeath(AttackEntity attacker) {
        System.out.println("Reward: " + attacker.getResourceReward());
        for(AttackDeathObserver observer : attackDeathObservers) {
            observer.onAttackerDeath(attacker);
        }
    }

    @Override
    public void update() {
        for (Lane lane : board.getLanes()) {
            // Sort attackers in the lane by laneProgress
            lane.sortAttackers();
            handleDefenderAttacks(lane);
            handleMeleeAttacks(lane);
        }
    }

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
                //ATTACK!
                if (defender.useAttack(lane, cellIndex)){
                    // Remove dead attackers
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

    private void removeDeadAttackers(Lane lane){
        List<AttackEntity> attackers = lane.getAttackers();
        Iterator<AttackEntity> iterator = attackers.iterator();
        while (iterator.hasNext()) {
            AttackEntity target = iterator.next();
            if (target.isDead()) {
                iterator.remove(); // Safely remove the element
                lane.removeAttacker(target); // Ensure the lane's state is updated
                notifyAttackerDeath(target);
            }
        }
    }

    private void setAllDefendersToIDLE(Lane lane) {
        for (int cellIndex = 0; cellIndex < lane.getNumberOfCells(); cellIndex++) {
            DefenceEntity defender = lane.getDefenderAtIndex(cellIndex);
            if (defender != null) {
                defender.setCurrentState(EntityState.IDLE);
            }
        }
    }


    private void handleMeleeAttacks(Lane lane) {

        for (AttackEntity attacker : lane.getAttackers()) {
            int attackerCellIndex = (int) PositionConverter.attackerToCellIndex(attacker.getLaneProgress(), lane.getNumberOfCells());
            // Get the defender at the attacker's position
            DefenceEntity defender = lane.getDefenderAtIndex(attackerCellIndex);

            if (defender != null) {
                // Attack the defender
                attacker.setCurrentState(EntityState.ATTACK);

                // Add a new counter for the attacker if it doesn't exist
                if (!attackCounters.containsKey(attacker)) {
                    attackCounters.put(attacker, new TickCounter(attacker.getAttackDelay(), TICKS_PER_SECOND));
                }
                // Attack if the attacker is ready
                if (attackCounters.get(attacker).getTicks() == 0) {
                    attacker.useAttack(defender);

                    // Remove the defender if it's dead
                    if (defender.isDead()) {
                        attacker.setCurrentState(EntityState.MOVE);
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
}
