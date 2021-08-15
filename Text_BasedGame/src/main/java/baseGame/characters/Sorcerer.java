package baseGame.characters;

import baseGame.Hero;
import java.util.Random;

public class Sorcerer extends Hero {
    public Sorcerer(String name, int strength, int intelligence, int vitality, int dexterity, int charisma, int maxHealth, int damage)
    {
        super(name, strength, intelligence, vitality, dexterity, charisma, maxHealth, damage);
    }

    @Override
    public boolean attack(Random random) {
        return random.nextBoolean();
    }

    @Override
    public boolean retreat(Random random) {
        return random.nextBoolean();
    }
}
