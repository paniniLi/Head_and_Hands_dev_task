package Game;

/**
 * @author Alina Doronina
 */
public class Player extends Entity {
    /**
     * The number of healing used: the maximum number is 4.
     */
    private int healing_cnt = 0;

    Player(String name, int attack, int protection, int health, int minDamage, int maxDamage) {
        super(name, attack, protection, health, minDamage, maxDamage);
    }

    /**
     * The healing method: the player can heal himself up to 4 times for 30% of the Maximum Health.
     * The player cannot heal to a health value greater than the maximum health.
     */
    public void heal() {
        if (healing_cnt<4) {
            System.out.println("Player " + this.getName() + " healed " + healing_cnt +" time.");
            setHealth(Math.min((int) (0.3 * this.getMaxHealth()) + this.getHealth(), this.getMaxHealth()));
            healing_cnt++;
        }
    }

    @Override
    public void takeAttackDamage(int damage) {
        if (getHealth()<getMaxHealth()*0.5) heal();
        System.out.println(getName()+" took damage "+damage);
        setHealth(Math.max(getHealth()-damage, 0));
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder()
                .append("Player ").append(this.getName()).append(":\n")
                .append("\tAttack: ").append(this.getAttack()).append("\n")
                .append("\tProtection: ").append(this.getProtection()).append("\n")
                .append("\t\tCurrent health: ").append(this.getHealth()).append("\n")
                .append("\t\tMaximum health: ").append(this.getMaxHealth()).append("\n")
                .append("\tDamage: ").append(this.getMinDamage()).append("-").append(this.getMaxDamage());
        return info.toString();
    }
}
