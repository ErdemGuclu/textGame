package baseGame.enemies;

import baseGame.Enemy;
import java.util.Random;

public class Dragon extends Enemy {
    public Dragon(String name, int health, int damage)
    {
        super(name, health, damage);
    }

    @Override
    public boolean attack(Random random)
    {
        return random.nextBoolean();
    }
}
