package com.group9.model.entities.characters.attackers;

import com.group9.model.attacks.MeleeAttack;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;
import com.group9.model.movement.AttackerMovementRule;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;

public class AttackEntity extends Character implements Movable {
    private int speed; // Speed at which the attacker moves
    private float laneProgress; // The current progress of the attacker on the lane (e.g., distance moved)
    private int resourceReward; // Reward given when the attacker is defeated

    public AttackEntity(String name, float maxHealth, float attackDamage, int range, float attackDelay, int speed, int resourceReward) {
        super(name, maxHealth, attackDamage, range, attackDelay, new MeleeAttack());
        this.speed = speed;                     // Set the speed of the attacker
        this.laneProgress = 0;                  // Set initial lane progress to 0
        this.resourceReward = resourceReward;   // Set the resource reward for defeating this attacker
        this.setCurrentState(EntityState.MOVE); // Set the initial state to MOVE
    }

    public float getLaneProgress() {
        return laneProgress;
    }

    public void setLaneProgress(float laneProgress) {
        this.laneProgress = laneProgress;
    }

    public int getResourceReward()  {
        return this.resourceReward;
    }

    public int getSpeed() {
        return this.speed;
    }

    // Implementation of Movable interface. It returns the movement rule for the attacker
    @Override
    public MovementRule getMovementRule() {
        return new AttackerMovementRule();
    }

}
