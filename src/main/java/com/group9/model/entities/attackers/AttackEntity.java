package com.group9.model.entities.attackers;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.attacks.MeleeAttack;
import com.group9.model.board.Lane;
import com.group9.model.entities.Entity;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.movement.AttackerMovementRule;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;

import java.util.List;

public class AttackEntity extends Entity implements Movable {
    private int speed;
    //private float laneProgress;
    private int resourceReward;
    private Lane lane;

    public AttackEntity(AttackerType type, int maxHealth, int attackDamage, int range, int speed, int resourceReward) {
        super(type, maxHealth, attackDamage, range, 0, new MeleeAttack());
        this.speed = speed;
        //this.laneProgress = 0;
        this.resourceReward = resourceReward;
        this.setCurrentState(EntityState.MOVE);
    }

    /*public float getLaneProgress() {
        return this.laneProgress;
    }*/

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
