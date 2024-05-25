package enums;

/**
 * ENUM for entities type
 * <p>
 * Available types: PLAYER, NPC, ITEM
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
