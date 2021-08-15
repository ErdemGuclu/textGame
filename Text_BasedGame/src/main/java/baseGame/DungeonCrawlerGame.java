package baseGame;

import baseGame.characters.Sorcerer;
import baseGame.characters.Warrior;
import baseGame.enemies.*;
import baseGame.randomEvents.Chest;
import baseGame.randomEvents.MysteriousStranger;
import baseGame.randomEvents.Trader;
import java.util.Random;
import java.util.Scanner;

public class DungeonCrawlerGame {
    public static Scanner scanner;
    public static int healthPotionAmount;
    public static int goldAmount;
    public static boolean amuletOfBloodlust;
    public static Random random;
    public static Enemy enemy;
    public static Hero hero;
    public static String name;
    public static boolean heroIsDead;
    public static final String[] ENEMY_NAMES = {"Goblin", "Orc", "Wraith", "Giant Spider", "Stone Golem", "Dragon"};

    static {
        random = new Random();
        healthPotionAmount = 1;
        goldAmount = 0;
        scanner = new Scanner(System.in);
    }

    public static void run()
    {
        for (;;) {
            System.out.print("Enter your hero's name: ");
            name = scanner.nextLine();

            if (name.isBlank())
                System.out.println("PLEASE ENTER A NAME!");
            else
                break;
        }

        System.out.println("""
                ****************************************************DUNGEON CRAWLER****************************************************************
                                
                Welcome to the dungeon brave hero. A long and dangerous path awaits with full of monsters and demons from distant realms.
                But with courage and willpower nothing stands in your way. It is time to fulfill your destiny and put an end to the evil across the land.
                """);

        for (;;) {
            System.out.println("""        
                    Choose your hero to start your journey:

                    1. WARRIOR:     Close combat master with brutal strength. Crash your enemies with high melee attack power and vitality. Can not use magic
                                    and spells. Comes with a random melee weapon(sword, axe or war hammer). But strength and bulky body make the warrior an easy
                                    target due to lack of dexterity. 
                                    
                    2. SORCERER:    Master of magical powers and spells. Destroy enemies with Sorcerer's high magic attack power and intelligence. Can not use
                                    melee weapons. Comes with a random magical weapon(staff, ward or Grimoire). Despite Sorcerer's efficiency with magic, you 
                                    must be careful not to take a lot of damage because of low vitality and armor. Still, high dexterity will make the Sorcerer 
                                    dodge attacks easily and increase the miss chance of enemies.
                                    
                    """);
            try {
                int heroChosen = Integer.parseInt(scanner.nextLine());

                System.out.println("********************************************************************************************************************");


                if (heroChosen == 1) {
                    hero = new Warrior(name, 8, 4, 7, 5, 5, 150, 30);
                    System.out.println("WARRIOR is chosen.");
                    showStats(hero, name);
                    break;
                } else if (heroChosen == 2) {
                    hero = new Sorcerer(name, 3, 8, 4, 7, 7, 100, 25);
                    System.out.println("SORCERER is chosen.");
                    showStats(hero, name);
                    break;
                } else
                    System.out.println("------------WRONG INPUT!!------------\n");
            }
            catch (NumberFormatException ex)
            {}
        }

        System.out.println("Press ENTER to step into the dungeon");
        scanner.nextLine();

        System.out.println("********************************************************************************************************************");

        int round = 1;

        //GAME STARTS****************************************************************
        while (!heroIsDead) {
            if (round == 14)
                break;

            System.out.println("********************************************************************************************************************");
            System.out.printf("ROUND: %d%n", round);

            if (round == 3 || round == 10)
                randomEvent(random, scanner, hero);
            else if (round == 6)
                MysteriousStranger.mysteriousStranger(random, scanner, hero);
            else if (round == 7)
                Trader.trader(random, scanner, hero);
            else if (round == 13) {
                enemy = new Dragon("Dragon", 250, 50);
                boss(hero, enemy, random);
            }
            else {
                enemy = enemyEncounter(random);
                enemyLevels(hero);
            }
            round++;

            if (heroIsDead) {
                System.out.println("Mankind waited a hero for a long time to save them from the evil on earth. But it seems you are not the one. As you take the final fatal blow,\n" +
                        "your body falls on the ground and rot in this dark place...\n\n");
                System.out.println("YOU DIED! GAME OVER\n\n");
            }
        }
    }

    private static void enemyLevels(Hero hero)
    {
        GAME_LOOP:
        for (;;) {
            if (hero.maxHealth <= 0) {
                heroIsDead = true;
                break GAME_LOOP;
            }

            System.out.printf("""
                        %s -> Health: %d
                        Damage: 0 - %d

                        What is your next move:

                        1. Attack!
                        2. Retreat
                        3. Use health potion
                        4. Show stats%n""", enemy.name, enemy.health, enemy.damage);

            try {
                int playerMove = Integer.parseInt(scanner.nextLine());

                System.out.println("********************************************************************************************************************");

                switch (playerMove) {
                    case 1:
                        int damageGiven = random.nextInt(hero.damage - 10) + 10;
                        enemy.health -= damageGiven;
                        System.out.printf("%s took %d damage.%n", enemy.name, damageGiven, enemy.name, enemy.health);

                        if (amuletOfBloodlust) {
                            int absorbedHealth = (int) (damageGiven * 0.2);
                            System.out.printf("Gained %d health with Sword of Bloodlust%n", absorbedHealth);
                            hero.maxHealth += absorbedHealth;
                        }

                        if (enemy.health > 0)
                            enemyAttack(hero, enemy, random);
                        else if (enemy.health <= 0 && enemy instanceof Dragon) {
                            System.out.println("\nWith your final powerful blow, mother of evil screams with pain and falls on the ground. After a brief silence ground starts to shake.\n" +
                                    "You sense the danger and run like hell all the way back to the dungeon exit. As you jump out of the dungeon, a final rock falls and barricades the\n" +
                                    "enterence. Thus, evil on the land is burried until it rises from the ashes...\n\n" +
                                    "THE END");
                            break GAME_LOOP;
                        }
                        else {
                            System.out.printf("You killed %s%n%n", enemy.name);
                            enemyRandomDrop(hero, random);
                            break GAME_LOOP;
                        }
                        break;

                    case 2:
                        if (enemy instanceof Dragon)
                            System.out.println("YOU CAN NOT RETREAT!\n");

                        else if (!(enemy instanceof Dragon) && random.nextBoolean()) {
                            System.out.println("You successfully retreated");
                            break GAME_LOOP;
                        } else {
                            System.out.printf("%s won't let you escape so easily%n", enemy.name);
                            enemyAttack(hero, enemy, random);
                        }
                        break;

                    case 3:
                        if (healthPotionAmount > 0) {
                            hero.maxHealth += 50;
                            healthPotionAmount--;
                            System.out.println("You used health potion and gained 50 hit points");
                        } else
                            System.out.println("You don't have any health potions");
                        break;

                    case 4:
                        showStats(hero, hero.name);
                        break;

                    default:
                        System.out.println("WRONG INPUT\n");
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("WRONG INPUT!\n");
                System.out.println("********************************************************************************************************************");
            }
        }
    }

    private static Enemy enemyEncounter(Random random)
    {
        int enemyIndex = random.nextInt(5);

        System.out.printf("You encountered a %s.%n", ENEMY_NAMES[enemyIndex]);

        switch (enemyIndex) {
            case 0:
                enemy = new Goblin("Goblin", 50, 5);
                break;
            case 1:
                enemy = new Orc("Orc", 75, 10);
                break;
            case 2:
                enemy = new Wraith("Wraith", 100, 15);
                break;
            case 3:
                enemy = new GiantSpider("Giant Spider", 120, 20);
                break;
            case 4:
                enemy = new StoneGolem("Stone Golem", 150, 25);
                break;
        }

        return enemy;
    }

    private static void randomEvent(Random random, Scanner scanner, Hero hero)
    {
        int randomEvent = random.nextInt(3);

        if (randomEvent == 0)
            Trader.trader(random, scanner, hero);
        else if (randomEvent == 1)
            MysteriousStranger.mysteriousStranger(random, scanner, hero);
        else
            Chest.chest(random, scanner, hero);
    }

    private static void boss(Hero hero, Enemy enemy, Random random)
    {
        System.out.println("End is nigh brave hero. You fought well through the dungeon and face all kinds of evil. But not the worst... Your journey comes to an end with\n" +
                "the greatest fight. There will be only one survivor. The fate of the mankind is in your hands...\n\n");

        System.out.println("BOSS FIGHT!!\n\n");

        enemyLevels(hero);
    }

    private static void enemyAttack(Hero hero, Enemy enemy, Random random)
    {
        int enemyHitChance = random.nextInt(10);

        if (hero instanceof Warrior) {
            if (enemyHitChance < 5)
                System.out.printf("%s missed%n%n", enemy.name);
            else {
                int damageTaken = random.nextInt(enemy.damage);
                hero.maxHealth -= damageTaken;
                System.out.printf("You took %d damage%n%n", damageTaken);
            }
        }
        else {
            if (enemyHitChance < 6)
                System.out.printf("%s missed%n%n", enemy.name);
            else {
                int damageTaken = random.nextInt(enemy.damage);
                hero.maxHealth -= damageTaken;
                System.out.printf("You took %d damage%n%n", damageTaken);
            }
        }
    }

    private static void enemyRandomDrop(Hero hero, Random random)
    {
        int drop = random.nextInt(10);

        if (drop < 6) {
            int droppedGoldAmount = random.nextInt(41) + 10;
            System.out.printf("Enemy dropped %d gold\n", droppedGoldAmount);
            goldAmount += droppedGoldAmount;
        }
        else {
            System.out.println("Enemy dropped a health potion");
            healthPotionAmount += 1;
        }
    }

    private static void showStats(Hero hero, String name)
    {
        System.out.printf("""

                YOUR STATS:
                
                Name: %s
                Strength: %d
                Intelligence: %d
                Vitality: %d
                Dexterity: %d
                Charisma: %d         
                Health: %d
                Damage: %d
                Gold: %d
                Health Potion: %d

                """, hero.name, hero.strength, hero.intelligence, hero.vitality, hero.dexterity, hero.charisma,
                hero.maxHealth, hero.damage, goldAmount, healthPotionAmount);
    }
}
