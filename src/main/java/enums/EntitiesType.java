package enums;

/**
 * Enumeration of entity types.
 * <p>
 * This enum defines different types of entities in the application, such as player,
 * NPC (non-player character), ammunition, armor, bullet, chest, flag, health, key,
 * and loot. Each entity type is associated with a unique number.
 * </p>
 */
public enum EntitiesType {
    PLAYER(1),
    NPC(2),
    AMMUNITION(3),
    ARMOR(4),
    BULLET(5),
    CHEST(6),
    FLAG(7),
    HEALTH(8),
    KEY(9),
    LOOT(10);

    private final int number;

    EntitiesType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
