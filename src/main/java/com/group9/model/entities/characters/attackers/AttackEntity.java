package com.group9.model.entities.characters.attackers;

import com.group9.model.attacks.MeleeAttack;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;
import com.group9.model.movement.AttackerMovementRule;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;

public class AttackEntity extends Character implements Movable {
    private final int speed;
    private float laneProgress;
    private final int resourceReward;

    /**
     * Constructs an AttackEntity with the specified attributes.
     *
     * @param name the name of the attacker
     * @param maxHealth the maximum health of the attacker
     * @param attackDamage the attack damage of the attacker
     * @param range the attack range of the attacker
     * @param attackDelay the delay between attacks
     * @param speed the speed at which the attacker moves
     * @param resourceReward the reward given when the attacker is defeated
     */
    public AttackEntity(String name, float maxHealth, float attackDamage, int range, float attackDelay, int speed, int resourceReward) {
        super(name, maxHealth, attackDamage, range, attackDelay, new MeleeAttack());
        this.speed = speed;
        laneProgress = 0;
        this.resourceReward = resourceReward;
        setCurrentEntityState(EntityState.MOVE);
    }

    public float getLaneProgress() {
        return laneProgress;
    }
    public void setLaneProgress(float laneProgress) {
        this.laneProgress = laneProgress;
    }
    public int getResourceReward()  {
        return resourceReward;
    }
    public int getSpeed() {
        return speed;
    }

    // Implementation of Movable interface. It returns the movement rule for the attacker
    @Override
    public MovementRule getMovementRule() {
        return new AttackerMovementRule();
    }

}
