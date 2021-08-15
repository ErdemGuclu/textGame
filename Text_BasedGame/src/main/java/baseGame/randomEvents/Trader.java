package baseGame.randomEvents;

import baseGame.DungeonCrawlerGame;
import baseGame.Hero;
import java.util.Random;
import java.util.Scanner;

public class Trader {
    public static void trader(Random random, Scanner scanner, Hero hero)
    {
        System.out.println("You come across a man with a huge backpack on his back. He salutes you as you approach:\n" +
                "\"Welcome stranger. I suggest you to look at my goods before you continue on your journey.\"\n");

        for (;;) {
            System.out.println("""
                    What are you going to do?

                    1. Let's trade
                    2. Walk away\n\n""");

            int inputTrader = Integer.parseInt(scanner.nextLine());

            if (inputTrader == 1) {
                System.out.println("\"Have a look at my precious things\"");
                for (;;) {
                    System.out.println("""
                            1. Health Potion - 30 gold
                            2. Axe of Elder Gods - 50 gold (Gives +10 damage)
                            3. Potion of Vitality - 40 gold (Increase max health to 200 instantly)
                            4. Leave\n\n""");
                    System.out.printf("You have %d golds%n", DungeonCrawlerGame.goldAmount);

                    int inputChoice = Integer.parseInt(scanner.nextLine());

                    if (inputChoice == 1) {
                        if (DungeonCrawlerGame.goldAmount >= 30) {
                            System.out.println("You purchased Health Potion for 30 gold");
                            DungeonCrawlerGame.healthPotionAmount += 1;
                            DungeonCrawlerGame.goldAmount -= 30;
                        } else
                            System.out.println("YOU DON'T HAVE ENOUGH GOLD\n");
                    } else if (inputChoice == 2) {
                        if (DungeonCrawlerGame.goldAmount >= 50) {
                            System.out.println("You purchased Axe of Elder Gods for 50 gold");
                            hero.damage += 10;
                            DungeonCrawlerGame.goldAmount -= 50;
                        } else
                            System.out.println("YOU DON'T HAVE ENOUGH GOLD\n");
                    } else if (inputChoice == 3) {
                        if (DungeonCrawlerGame.goldAmount >= 40) {
                            System.out.println("You purchased Potion of Vitality for 40 gold");
                            hero.maxHealth = 200;
                            DungeonCrawlerGame.goldAmount -= 40;
                        } else
                            System.out.println("YOU DON'T HAVE ENOUGH GOLD!\n");
                    } else if (inputChoice == 4) {
                        System.out.println("Trader salutes you with a friendly smile on his face and walks away towards the darkness...");
                        break;
                    } else {
                        System.out.println("WRONG INPUT");
                    }
                }
                break;
            } else if (inputTrader == 2) {
                System.out.println("You decide not to deal with a man you don't know about and keep walking.");
                break;
            } else
                System.out.println("WRONG INPUT");
        }
    }
}
