package Game;

/**
 * Fabric for creating Player objects.
 * In case of extending the Player class, use the following implementation of the PlayerBuilder class:
 * abstract class GenericPlayerBuilder<B extends GenericPlayerBuilder<B>>
 *         extends GenericEntityBuilder<B> {
 *
 *     @Override
 *     GenericPlayerBuilder self() { return this; }
 *
 *     @Override
 *     public abstract Player build();
 * }
 *
 * @author Alina Doronina
 */
public class PlayerBuilder extends GenericEntityBuilder<PlayerBuilder>{
    @Override
    PlayerBuilder self() { return this; }

    @Override
    public Player build() {
        return new Player(name, attack, protection, health, minDamage, maxDamage);
    }
}
