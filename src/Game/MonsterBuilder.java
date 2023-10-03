package Game;

/**
 * Fabric for creating Monster objects.
 * In case of extending the Monster class, use the following implementation of the MonsterBuilder class:
 * abstract class GenericMonsterBuilder<B extends GenericMonsterBuilder<B>>
 *         extends GenericEntityBuilder<B> {
 *
 *     @Override
 *     GenericMonsterBuilder self() { return this; }
 *
 *     @Override
 *     public abstract Monster build();
 * }
 *
 * @author Alina Doronina
 */
public class MonsterBuilder extends GenericEntityBuilder<MonsterBuilder> {
    @Override
    MonsterBuilder self() { return this; }

    @Override
    public Monster build() {
        return new Monster(name, attack, protection, health, minDamage, maxDamage);
    }
}
