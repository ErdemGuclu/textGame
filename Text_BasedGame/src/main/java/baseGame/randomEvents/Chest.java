package baseGame.randomEvents;

import baseGame.DungeonCrawlerGame;
import baseGame.Hero;
import java.util.Random;
import java.util.Scanner;

public class Chest {
    private static final String LOOT[] = {"Gold", "Health Potion", "Amulet of Bloodlust(Absorbs small amount of damage points given to enemy and adds to your health)"};

    public static void chest(Random random, Scanner scanner, Hero hero)
    {
        System.out.println("After defeating your last enemy, you reached a large and dark room with nothing in it, except a big and gorgeous chest. It almost feels like\n" +
                "it illuminates the room with a strange light. You hear whispers trying to persuade you to open it.\n\n");

        for (;;) {
            System.out.println("""
                            What are you going to do?
    
                            1. Open it!
                            2. Attack!
                            3. Walk away\n\n""");

            try {
                int playerChoice = Integer.parseInt(scanner.nextLine());
                boolean isChestSafe = random.nextBoolean();


                if (playerChoice == 1 && isChestSafe) {
                    int lootIndex = random.nextInt(3);

                    System.out.printf("After a brief hesitation you decided to trust your instincts and open the chest. As you open it and see what is inside, you understand that it was%n" +
                            "the right choice. You received %s%n", LOOT[lootIndex]);

                    if (lootIndex == 0)
                        DungeonCrawlerGame.goldAmount += 50;
                    else if (lootIndex == 1)
                        DungeonCrawlerGame.healthPotionAmount += 1;
                    else
                        DungeonCrawlerGame.amuletOfBloodlust = true;

                    break;
                } else if (playerChoice == 1 && !isChestSafe) {
                    System.out.println("As soon as you open the chest, a poisonous gas leaked out. You feel weak and paralized. You lost 50 health and you max damage decreased -10 points\n");
                    hero.maxHealth -= 50;
                    hero.damage -= 10;
                    break;
                } else if (playerChoice == 2 && isChestSafe) {
                    System.out.println("You sensed evil about the chest and attack it immediately. But obviously you were wrong. Chest was full of potions and loots that could have\n" +
                            "helped you on your journey, but with your powerful attack everything is destroyed now.");
                    break;
                } else if (playerChoice == 2 && !isChestSafe) {
                    System.out.println("Something is not right about the chest and you can sense it. By the time you hold your weapon's grip, chest opens his mouth with a big and sharp teeth.\n" +
                            "But you are fast and agile. Before it has a chance to attack, you deal a death blow with your weapon. Monstrous chest falls on the ground and transforms into a pile\n" +
                            "of gold. You received 50 Gold");
                    DungeonCrawlerGame.goldAmount += 50;
                    break;
                } else if (playerChoice == 3 && isChestSafe) {
                    System.out.println("It's a wretched place full of traps and monsters. You decide not to take risks and continue on your journey without opening the chest. Still, the voice\n" +
                            "of your conscience talks to you as you walk away: \"What if there was a great loot int the chest?\"\n");
                    break;
                } else if (playerChoice == 3 && !isChestSafe) {
                    System.out.println("It's a wretched place full of traps and monsters. You decide not to take risks and continue on your journey without opening the chest. But as soon as you\n" +
                            "turn your back, that beautiful chest transforms into a monster an bites you. You turn your back with a great pain. But before you take out your weapon, you see the\n" +
                            "monster goes into the darkness and disappears. You lost 50 health");
                    hero.maxHealth -= 50;
                    break;
                } else
                    System.out.println("WRONG INPUT!");
            }
            catch (NumberFormatException ignored)
            {}
        }

        System.out.println("You continue on your journey through the dungeon\n");
    }
}
