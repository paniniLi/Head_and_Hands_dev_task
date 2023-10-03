package Examples;

import Game.Entity;
import Game.Player;
import Game.PlayerBuilder;
import Game.Monster;
import Game.MonsterBuilder;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * Here you can see how to conduct a battle between one player and several monsters.
 * You can change the characteristics of monsters, make them stronger or weaker by changing the value of variables of this class.
 * @author Alina Doronina
 */
public class Example_2 {
    /**
     * The number of monsters attacking the player.
     */
    static int NUMBER_OF_MONSTERS = 1;
    /**
     * The lower boundary of the attack field for creating Monster class objects.
     */
    static int MIN_BORDER_OF_ATTACK = 4;
    /**
     * The upper limit of the attack field for creating objects of the Monster class.
     */
    static int MAX_BORDER_OF_ATTACK = 8;
    /**
     * The lower boundary of the protection field for creating Monster class objects.
     */
    static int MIN_BORDER_OF_PROTECTION = 3;
    /**
     * The upper limit of the protection field for creating objects of the Monster class.
     */
    static int MAX_BORDER_OF_PROTECTION = 5;
    /**
     * The lower boundary of the health field for creating Monster class objects.
     */
    static int MIN_BORDER_OF_HEALTH = 25;
    /**
     * The upper limit of the health field for creating objects of the Monster class.
     */
    static int MAX_BORDER_OF_HEALTH = 30;
    /**
     * The minimum damage for creating objects of the Monster class.
     */
    static int MIN_BORDER_OF_DAMAGE = 6;
    /**
     * The maximum damage for creating objects of the Monster class.
     */
    static int MAX_BORDER_OF_DAMAGE = 9;

    /**
     * The method for starting the game and determines the winner.
     */
    public static void start() {
        if (!isValidInputDataOfParameterBoundaries()) {
            System.out.println("Invalid data format for monster parameter boundaries"); return;}

        try {
            Player player = (new PlayerBuilder())
                    .setName("player_1")
                    .setAttack(5)
                    .setProtection(6)
                    .setHealth(10)
                    .setDamageRange(1, 9)
                    .build();
            ArrayList<Monster> monsters = createMonsters();

            fight(player, monsters);

            Entity winner = getWinner(player, monsters);
            if (winner==null) System.out.println("The result of the game: everyone lost.");
            else System.out.println("The result of the game: "+winner.getName()+" won");
        } catch(DataFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The method of creating the list of monsters with preset parameter ranges.
     * @return ArrayList of created monsters.
     * @throws DataFormatException The exception is thrown in case of incorrectly set values of fields of the Monster class.
     */
    private static ArrayList<Monster> createMonsters() throws DataFormatException {
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i=0; i<NUMBER_OF_MONSTERS; i++) {
            Monster monster = (new MonsterBuilder())
                    .setName("monster_"+i)
                    .setAttack(getRandomNumberFromRange(MIN_BORDER_OF_ATTACK, MAX_BORDER_OF_ATTACK))
                    .setProtection(getRandomNumberFromRange(MIN_BORDER_OF_PROTECTION, MAX_BORDER_OF_PROTECTION))
                    .setHealth(getRandomNumberFromRange(MIN_BORDER_OF_HEALTH, MAX_BORDER_OF_HEALTH))
                    .setDamageRange(MIN_BORDER_OF_DAMAGE, MAX_BORDER_OF_DAMAGE)
                    .build();
            monsters.add(monster);
        }
        return monsters;
    }

    /**
     * The method for get random int number from preset range.
     * @param minBorderOfRange The minimum border of range of the selected number.
     * @param maxBorderOfRange The maximum border of range of the selected number.
     * @return Random int number from preset range.
     */
    private static int getRandomNumberFromRange(int minBorderOfRange, int maxBorderOfRange) {
        return (int) ((Math.random() * (maxBorderOfRange-minBorderOfRange)) + minBorderOfRange);
    }

    /**
     * The method for checking valid input data (NUMBER_OF_MONSTERS, MIN_BORDER_OF_ATTACK etc.)
     */
    private static boolean isValidInputDataOfParameterBoundaries() {
        return !(NUMBER_OF_MONSTERS <= 0 ||
                MIN_BORDER_OF_ATTACK <= 0 || MAX_BORDER_OF_ATTACK <= 0 || MIN_BORDER_OF_ATTACK > MAX_BORDER_OF_ATTACK ||
                MIN_BORDER_OF_PROTECTION <= 0 || MAX_BORDER_OF_PROTECTION <= 0 || MIN_BORDER_OF_PROTECTION > MAX_BORDER_OF_PROTECTION ||
                MIN_BORDER_OF_HEALTH <= 0 || MAX_BORDER_OF_HEALTH <= 0 || MIN_BORDER_OF_HEALTH > MAX_BORDER_OF_HEALTH ||
                MIN_BORDER_OF_DAMAGE <= 0 || MAX_BORDER_OF_DAMAGE <= 0 || MIN_BORDER_OF_DAMAGE > MAX_BORDER_OF_DAMAGE);
    }

    /**
     * The method of the battle between the player and the monsters.
     * @param player The player participating in the battle.
     * @param monsters An array of monsters participating in the battle.
     */
    private static void fight(Player player, ArrayList<Monster> monsters) {
        while (player.isAlive() && monsters.stream().anyMatch(Entity::isAlive)) {
            monsters.forEach(monster -> {
                if (monster.isAlive()) {
                    player.attack(monster);
                    monster.attack(player);
                }
            });
        }
    }

    /**
     * The method for searching the winning/surviving(the same meaning) entity.
     * @param player The player participating in the battle.
     * @param monsters An array of monsters participating in the battle.
     * @return The winning entity, null if there is no winner (all entities have died).
     */
    private static Entity getWinner(Player player, ArrayList<Monster> monsters) {
        if (player.isAlive()) return player;
        return monsters.stream().filter(Entity::isAlive).findAny().orElse(null);
    }
}
