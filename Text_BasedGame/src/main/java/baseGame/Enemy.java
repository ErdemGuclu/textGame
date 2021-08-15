package baseGame;

import java.util.Random;

public abstract class Enemy {
    public int health;
    public int damage;
    public String name;

    protected Enemy(String name, int health, int damage)
    {
        this.health = health;
        this.damage = damage;
        this.name = name;
    }

    public abstract boolean attack(Random random);
}
