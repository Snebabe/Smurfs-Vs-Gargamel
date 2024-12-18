package com.group9.model.entities.characters.attackers;

import com.group9.model.attacks.MeleeAttack;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.Character;
import com.group9.model.entities.EntityState;
import com.group9.model.movement.AttackerMovementRule;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;

public class AttackEntity extends Character implements Movable {
    private int speed;
    private float laneProgress;
    private int resourceReward;
    private Lane lane;

    public AttackEntity(String name, int maxHealth, int attackDamage, int range, float attackDelay, int speed, int resourceReward) {
        super(name, maxHealth, attackDamage, range, attackDelay, new MeleeAttack());
        this.speed = speed;
        this.laneProgress = 0;
        this.resourceReward = resourceReward;
        this.setCurrentState(EntityState.MOVE);
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

    @Override
    public MovementRule getMovementRule() {
        return new AttackerMovementRule();
    }

}
