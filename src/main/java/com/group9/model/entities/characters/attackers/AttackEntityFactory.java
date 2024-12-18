package com.group9.model.entities.characters.attackers;

import com.group9.model.entities.EntityConfiguration;

import java.util.List;
import java.util.Random;

public class AttackEntityFactory {

    public static AttackEntity createAttacker(AttackerType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid attacker type");
        }
        return new AttackEntity(type.getName(), type.getMaxHealth(), type.getAttackDamage(), type.getRange(), type.getAttackSpeed(), type.getSpeed() ,type.getResourceReward());
    }




    public AttackEntity createRandomAttacker() {
        List<AttackerType> attackerTypeList = EntityConfiguration.getAttackerTypes();
        // Generate a random index
        int randomIndex = new Random().nextInt(attackerTypeList.size());

        // Get a random AttackerType
        AttackerType randomType = attackerTypeList.get(randomIndex);

        // Create the attacker
        return createAttacker(randomType);
    }

}
