package baseGame.randomEvents;

import baseGame.DungeonCrawlerGame;
import baseGame.Hero;
import java.util.Random;
import java.util.Scanner;

public class MysteriousStranger {
    public static void mysteriousStranger(Random random, Scanner scanner, Hero hero)
    {
        System.out.println("Your road crosses with a mysterious stranger. He stands in your way without any move, like a statue. " +
                "As you walk towards him, he suddenly reaches his pocket and tries to take out something.\n\n");

        for (;;) {
            System.out.println("""
                            What are you going to do?
    
                            1. Attack!
                            2. Wait\n\n""");

            try {
                int playerChoice = Integer.parseInt(scanner.nextLine());
                boolean isStrangerGood = random.nextBoolean();


                if (playerChoice == 1 && isStrangerGood) {
                    System.out.println("By the time you swing your weapon to hit the stranger, he disappeared like mirage. You feel weird, weak and empty. A distant voice talks to you and says:\n" +
                            "\"Is this how you treat people who want to help you. I curse you to rot in this hellhole\"\n" +
                            "You are cursed by the mysterious stranger and lost 50 health.");
                    hero.maxHealth -= 50;

                    if (hero.maxHealth <= 0)
                        DungeonCrawlerGame.heroIsDead = true;

                    break;
                } else if (playerChoice == 1 && !isStrangerGood) {
                    String loot = random.nextBoolean() ? "Gold" : "Health Potion";

                    System.out.printf("Thanks to your instincts and quick reflexes, you sensed the upcoming danger and killed the evil stranger with a single fatal blow. As he falls on%n" +
                            " the ground you saw a knife in his hand. But there are still demons and abominations to defeat in this wretched place and there is no time to lose. You searched %n" +
                            "the stranger's pockets and find %s %n", loot);

                    if (loot.equals("Gold"))
                        DungeonCrawlerGame.goldAmount += 50;
                    else
                        DungeonCrawlerGame.healthPotionAmount += 1;

                    break;
                } else if (playerChoice == 2 && isStrangerGood) {
                    for (;;) {
                        System.out.println("You sense something different about the stranger which makes you safe and relaxed. You decide to wait and stranger takes out a glowing yellow " +
                                "potion and talks to you with a raspy voice :\n \"There is a dangerous path ahead brave hero. But fear not, this potion will help you defeat your foes. \"\n");
                        System.out.println("""
                                What are you going to do?

                                1. Take and drink the potion
                                2. Reject and walk away""");
                        try {
                            int potionChoice = Integer.parseInt(scanner.nextLine());
                            if (potionChoice == 1) {
                                if (random.nextBoolean()) {
                                    System.out.println("You take the potion and drink without hesitation. You feel like nothing stands in your way now. Your damage increased by 15 points\n");
                                    hero.damage += 15;
                                } else {
                                    System.out.println("You take the potion and drink without hesitation. As you drink the last drop from the bottle, you realize that was a mistake. As you fall\n" +
                                            "on the ground eveything is going dark. The last thing you saw before you lose consciousness is the evil face of the mysterious stranger laughing at you.\n" +
                                            "After you wake up, nothing feels the same. You feel weak and tired. Stranger poisoned you and your max damage decreased 10 points.\n");
                                    hero.damage -= 10;
                                }
                                break;
                            } else if (potionChoice == 2) {
                                System.out.println("Something tells you not to trust the stranger and walk away. You refuse the gift and continue your path. The mysterious stranger watches\n " +
                                        "you with sad eyes as you walk away from him. Maybe you refused a great gift that would help you on your journey, no one will ever know...\n");
                                break;
                            } else
                                System.out.println("WRONG INPUT");
                        } catch (NumberFormatException ex)
                        {}
                    }
                    break;
                } else if (playerChoice == 2 && !isStrangerGood) {
                    System.out.println("A strong curiosity makes you do nothing and wait for the stranger. But that was a big mistake. The mysterious stranger acts very quickly and sticks\n" +
                            "a poisonous needle to your chest. Going unconscious takes only a sacond. As you wake you see the stranger is gone and you feel lightweight. But you realize that\n" +
                            "is because you gold is gone. You lost all your gold!\n");
                    DungeonCrawlerGame.goldAmount = 0;
                    break;
                } else
                    System.out.println("WRONG INPUT");
            }
            catch (NumberFormatException ex)
            {}
        }
    }
}
