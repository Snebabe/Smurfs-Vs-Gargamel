package com.group9.model;

import com.group9.model.entities.Entity;

public interface hasAttack {
    // Det finns redan ett attribut "attack" i entity,
    // det hade kunnat heta strength eller nåt, spela roll
    public void useAttack(Entity entity);
}
