package com.group9.model;

public class AttackGargamel extends AttackEntity{

    AttackGargamel(int health, int attack, int range, Lane lane, int x, int level, int cost) {
        super(health, attack, range, lane, x );
    }




    @Override
    public void useAttack(Entity entity) {

    }
}
