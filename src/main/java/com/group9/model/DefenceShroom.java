package com.group9.model;

public class DefenceShroom extends DefenceEntity{
    DefenceShroom(int health, int attack, int range, Lane lane, int x, int level, int cost) {
        super(health, attack, range, lane, x, level, cost);
    }

    @Override
    public void useAttack(Entity entity) {
        //not implemented
    }
}
