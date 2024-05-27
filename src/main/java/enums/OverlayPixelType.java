package enums;

import services.TileLoader;

/**
 * Enumeration of overlay pixel types.
 * <p>
 * This enum defines different types of overlay pixels used in the application,
 * such as player, enemy, armor, and bullet. Each overlay pixel type is associated
 * with a unique number and a corresponding image file.
 * </p>
 */
public enum OverlayPixelType {
    PLAYER(1, new TileLoader("src/main/resources/player.png")),
    ENEMY(2, new TileLoader("src/main/resources/enemy.png")),
    ARMOR(4, new TileLoader("src/main/resources/armor.png")),
    BULLET(5, new TileLoader("src/main/resources/bullet.png"));

    private final int number;
    private final TileLoader tileLoader;

    OverlayPixelType(int number, TileLoader tile) {
        this.number = number;
        this.tileLoader = tile;
    }

    private TileLoader getTileLoader() {
        return tileLoader;
    }

    private int getNumber() {
        return number;
    }

    /**
     * Retrieves the TileLoader instance associated with the specified number.
     *
     * @param number the number of the overlay pixel type
     * @return the TileLoader instance associated with the specified number
     * @throws IllegalArgumentException if the specified number does not correspond to any overlay pixel type
     */
    public static TileLoader getTileByNumber(int number) {
        for (OverlayPixelType tile : values()) {
            if (tile.getNumber() == number) {
                return tile.getTileLoader();
            }
        }
        throw new IllegalArgumentException("Invalid number: " + number);
    }
}
