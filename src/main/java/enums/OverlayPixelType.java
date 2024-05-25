package enums;

import services.TileLoader;

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

    public static TileLoader getTileByNumber(int number) {
        for (OverlayPixelType tile : values()) {
            if (tile.getNumber() == number) {
                return tile.getTileLoader();
            }
        }
        throw new IllegalArgumentException("Invalid number: " + number);
    }
}
