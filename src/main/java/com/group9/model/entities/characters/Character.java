package com.group9.model.entities.characters;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.EntityState;

/**
 * Represents a character that exists on the grid.
 */
public abstract class Character {
    private EntityState currentState;
    private float health; // Current health
    private final String name;
    private final float maxHealth;
    private final float attackDamage;
    private boolean isDead;
    private final int attackRange;
    private final float attackDelay;
    private final AttackStrategy attackStrategy;

    /**
     * Constructs a Character with the specified attributes.
     *
     * @param name the name of the character
     * @param maxHealth the maximum health of the character
     * @param attackDamage the attack damage of the character
     * @param attackRange the attack range of the character
     * @param attackDelay the delay between attacks in seconds
     * @param attackStrategy the attack strategy of the character
     */
    public Character(String name, float maxHealth, float attackDamage, int attackRange, float attackDelay, AttackStrategy attackStrategy) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.attackDelay = attackDelay;
        this.attackStrategy = attackStrategy;
        health = maxHealth;
        isDead = false; // Start as alive
    }

    /**
     * Uses the Character's attack strategy to attack the target.
     *
     * @param target the target of the attack
     */
    public void useAttack(Character target) {
        target.takeDamage(attackDamage);
    }

    public void takeDamage(float damage) {
        health -= damage;
        if(health <= 0) {
            isDead = true;
        }
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public String getName() {
        return name;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public float getAttackDelay() {
        return attackDelay;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public EntityState getCurrentEntityState() {
        return currentState;
    }

    public void setCurrentEntityState(EntityState state) {
        currentState = state;
    }

    public boolean isDead() {
        return isDead;
    }

}
