package Game;

import Utils.MessageInterface;

import java.util.zip.DataFormatException;

/**
 * A template for a future implementation of the Monster class. Here you can add new types of monsters, redefine already prescribed methods in the Entity class, and so on.
 * @author Alina Doronina
 */
public class Monster extends Entity {
    Monster(String name, int attack, int protection, int health, int minDamage, int maxDamage) {
        super(name, attack, protection, health, minDamage, maxDamage);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder()
                .append("Monster ").append(this.getName()).append(":\n")
                .append("\tAttack: ").append(this.getAttack()).append("\n")
                .append("\tProtection: ").append(this.getProtection()).append("\n")
                .append("\t\tCurrent health: ").append(this.getHealth()).append("\n")
                .append("\t\tMaximum health: ").append(this.getMaxHealth()).append("\n")
                .append("\tDamage: ").append(this.getMinDamage()).append("-").append(this.getMaxDamage());
        return info.toString();
    }
}
