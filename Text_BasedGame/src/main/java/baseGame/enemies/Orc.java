package baseGame.enemies;

import baseGame.Enemy;
import java.util.Random;

public class Orc extends Enemy {
    public Orc(String name, int health, int damage)
    {
        super(name, health, damage);
    }

    @Override
    public boolean attack(Random random) {
        return random.nextBoolean();
    }
}
