package com.group9.model.entities;
import com.group9.model.attacks.MeleeAttack;
import com.group9.model.attacks.RangedAttack;
import com.group9.model.entities.characters.attackers.AttackerType;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.entities.projectiles.ProjectileType;

import java.util.ArrayList;
import java.util.List;


public class EntityConfiguration {
    private static final List<DefenderType> DEFENDER_TYPE_LIST = new ArrayList<>();
    private static final List<AttackerType> ATTACKER_TYPE_LIST = new ArrayList<>();
    private static final List<ProjectileType> PROJECTILE_TYPE_LIST = new ArrayList<>();

    static {
        // Register ProjectileTypes
        ProjectileType ARROW = new ProjectileType("ARROW", 4);
        ProjectileType BULLET = new ProjectileType("BULLET", 6);

        PROJECTILE_TYPE_LIST.add(ARROW);
        PROJECTILE_TYPE_LIST.add(BULLET);

        // Register DefenderTypes
        DefenderType SHROOM = new DefenderType("SHROOM",  200, 0, 0, 0f, 100, new MeleeAttack());
        DefenderType BOXER = new DefenderType("BOXER", 150, 10, 1, 0.5f, 100, new MeleeAttack());
        DefenderType ARCHER = new DefenderType("ARCHER",  50, 20, 3, 1f, 150, new RangedAttack(ARROW));
        DefenderType SHOOTER = new DefenderType("SHOOTER",  50, 20, 5, 0.5f, 400, new RangedAttack(BULLET));

        DEFENDER_TYPE_LIST.add(SHROOM);
        DEFENDER_TYPE_LIST.add(BOXER);
        DEFENDER_TYPE_LIST.add(ARCHER);
        DEFENDER_TYPE_LIST.add(SHOOTER);

        // Register AttackerTypes
        AttackerType GARGAMEL = new AttackerType("GARGAMEL",  100, 10, 0, 1f, 1, 25);
        AttackerType FASTGARGAMEL = new AttackerType("FASTGARGAMEL", 100, 6, 1, 0.5f, 2, 25);

        ATTACKER_TYPE_LIST.add(GARGAMEL);
        ATTACKER_TYPE_LIST.add(FASTGARGAMEL);

    }

    public static List<DefenderType> getDefenderTypes() {
        return DEFENDER_TYPE_LIST;
    }

    public static List<AttackerType> getAttackerTypes() {
        return ATTACKER_TYPE_LIST;
    }

    public static List<ProjectileType> getProjectileTypes() {
        return PROJECTILE_TYPE_LIST;
    }


}
