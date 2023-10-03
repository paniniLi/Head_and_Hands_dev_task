package Game;

/**
 * @author Alina Doronina
 */
abstract public class Entity {
    /**
     * The private name of the entity.
     */
    private String name;
    /**
     * The private value of the attack: an integer from 1 to 30.
     */
    private int attack;
    /**
     * The private value of the protection: an integer from 1 to 30.
     */
    private int protection;
    /**
     * The private current health value: an integer from 0 to N.
     */
    private int health;
    /**
     * The maximum value of health, a parameter necessary for the healing process.
     */
    private int maxHealth;
    /**
     * Maximum damage a creature can inflict on another creature.
     */
    private int maxDamage;
    /**
     * Minimum damage a creature can inflict on another creature.
     */
    private int minDamage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }
    Entity (String name, int attack, int protection, int health, int minDamage, int maxDamage) {
        this.name = name;
        this.attack = attack;
        this.protection = protection;
        this.health = health;
        this.maxHealth = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
     * Method of attack: calculation of the attack modifier and attack success, in positive case, the entity is dealt damage.
     * @param entity Attacked entity.
     */
    public void attack(Entity entity) {
        System.out.println(name+" attack "+entity.getName());
        if (health<=0 || entity.health<=0) return;
        int attackModifier = getAttackModifier(entity);
        if (isSuccessfulAttack(attackModifier)) {
            causeDamage(entity);
        }
    }

    /**
     * Method of receiving damage from an attack.
     * @param damage Damage caused.
     */
    public void takeAttackDamage(int damage) {
        System.out.println(name+" took damage "+damage);
        health = Math.max(health-damage, 0);
    }

    /**
     * The method determines whether the creature is alive or not. The creature dies if health is less than 0.
     */
    public boolean isAlive() {
        return health>0;
    }

    /**
     * Method of calculating the attack modifier: it is equal to the difference between the attacker's Attack and the defender's Defense plus 1.
     * @param entity Attacked entity.
     * @return Attack modifier.
     */
    private int getAttackModifier(Entity entity) {
        return attack - entity.getProtection()+1;
    }

    /**
     * Method of determining the success of the attack: success is determined by throwing N dice with numbers from 1 to 6, where N is the Attack Modifier. A hit is considered successful if at least one of the dice has 5 or 6.
     * @param attackModifier Attack modifier.
     * @return Attack Success.
     */
    private boolean isSuccessfulAttack(int attackModifier) {
        for (int i=0;i<attackModifier;i++) {
            int resultOfDiceToss = (int) ((Math.random() * 5) + 1);
            if (resultOfDiceToss==5 || resultOfDiceToss==6) {
                System.out.println("The attack is successful");
                return true;
            }
        }
        System.out.println("The attack failed");
        return false;
    }

    /**
     * Method of damage: first, the magnitude of the impact is calculated, then the damage is inflicted.
     * @param entity Attacked entity.
     */
    private void causeDamage(Entity entity) {
        int damageAmount =  (int) ((Math.random() * (maxDamage - minDamage)) + minDamage);
        System.out.println(name+" causes damage to "+entity.getName()+" in the amount of "+damageAmount);
        entity.takeAttackDamage(damageAmount);
    }
}
