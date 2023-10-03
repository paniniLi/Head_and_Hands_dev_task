package Game;

import java.util.zip.DataFormatException;

/**
 * Abstract fabric for creating inheritors of the Entity class builder.
 * @param <B> Inheritors of GenericEntityBuilder class. It is used in the "set(field name)" method for the correct use of the inheritance mechanism.
 * @author Alina Doronina
 */
abstract public class GenericEntityBuilder <B extends GenericEntityBuilder<B>> {
    /**
     * Name of the entity.
     */
    String name;
    /**
     * Value of the attack: an integer from 1 to 30.
     */
    int attack;
    /**
     * Value of the protection: an integer from 1 to 30.
     */
    int protection;
    /**
     * Current health value: an integer from 0 to N.
     */
    int health;
    /**
     * Maximum damage a creature can inflict on another creature.
     */
    int maxDamage;
    /**
     * Minimum damage a creature can inflict on another creature.
     */
    int minDamage;

    public B setName(String name) throws DataFormatException {
        if (name==null || name.equals(""))
            throw new DataFormatException("Name of the entity shouldn't be null of empty string");
        this.name = name; return self();
    }

    public B setAttack(int attack) throws DataFormatException {
        if (attack < 1 || attack > 30)
            throw new DataFormatException("Attack of the entity should be a natural number in range [1, 30]");

        this.attack = attack; return self();
    }

    public B setProtection(int protection) throws DataFormatException {
        if (protection < 1 || protection > 30)
            throw new DataFormatException("Protection of the entity should be a natural number in range [1, 30]");

        this.protection = protection; return self();
    }

    public B setHealth(int health) throws DataFormatException{
        if (health < 0)
            throw new DataFormatException("Health of the entity should be a natural number");

        this.health = health; return self();
    }

    public B setDamageRange(int minDamage, int maxDamage) throws DataFormatException{
        if (minDamage < 0 || maxDamage < 0)
            throw new DataFormatException("Damage of the entity should be a range of natural number");
        else if (minDamage > maxDamage)
            throw new DataFormatException("Minimum damage (minDamage) must be less than or equal to maximum damage (maxDamage)");
        this.minDamage=minDamage; this.maxDamage=maxDamage; return self();
    }

    /**
     * The method of assembling the finished product based on the previously set fields of the GenericEntityBuilder class.
     * @return Created Entity class object.
     */
    public abstract Entity build();

    /**
     * The method for the correct use of "this" during inheritance, in heirs, the type of self changes to the type of the heir.
     * @return The data type of the descendant of the GenericEntityBuilder class.
     */
    abstract B self();
}
