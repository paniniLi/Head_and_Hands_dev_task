package Examples;

import Game.Monster;
import Game.MonsterBuilder;
import Game.Player;
import Game.PlayerBuilder;

import java.util.zip.DataFormatException;

/**
 * Here you can see how to create players and monsters using builder-class.
 * @author Alina Doronina
 */
public class Example_1 {
    public static void start() {
        try {
            Player player = (new PlayerBuilder())
                    .setName("player_1")
                    .setAttack(5)
                    .setProtection(6)
                    .setHealth(98)
                    .setDamageRange(1, 4)
                    .build();
            Monster monster = (new MonsterBuilder())
                    .setName("monster_1")
                    .setAttack(7)
                    .setProtection(9)
                    .setHealth(3)
                    .setDamageRange(4, 9)
                    .build();

            System.out.println(player);
            System.out.println(monster);
        } catch(DataFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
