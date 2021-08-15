package baseGame.enemies;

import baseGame.Enemy;
import java.util.Random;

public class Wraith extends Enemy {
    public Wraith(String name, int health, int damage)
    {
        super(name, health, damage);
    }

    @Override
    public boolean attack(Random random)
    {
        return random.nextBoolean();
    }
}
