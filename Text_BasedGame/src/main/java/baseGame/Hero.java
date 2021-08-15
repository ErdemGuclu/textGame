package baseGame;

import java.util.Random;

public abstract class Hero {
    public String name;
    public int strength;
    public int intelligence;
    public int vitality;
    public int dexterity;
    public int charisma;
    public int maxHealth;
    public int damage;

    protected Hero(String name, int strength, int intelligence, int vitality, int dexterity, int charisma, int maxHealth, int damage)
    {
        this.name = name;
        this.strength = strength;
        this.intelligence = intelligence;
        this.vitality = vitality;
        this.dexterity = dexterity;
        this.charisma = charisma;
        this.maxHealth = maxHealth;
        this.damage = damage;
    }

    public abstract boolean attack(Random random);
    public abstract boolean retreat(Random random);
}
