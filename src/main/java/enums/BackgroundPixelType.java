package enums;

import services.TileLoader;

public enum BackgroundPixelType {
    PASSAGE_WAY(0, new TileLoader("src/main/resources/passage_way.png")),
    INDESTRUCTIBLE_WALL(1, new TileLoader("src/main/resources/indestructible_wall.png")),
    DESTRUCTIBLE_WALL(2, new TileLoader("src/main/resources/destructible_wall.png")),
    HARD_WALL(3, new TileLoader("src/main/resources/hard_wall.png")),
    BUSH(4, new TileLoader("src/main/resources/bush.png")),
    WATER(5, new TileLoader("src/main/resources/water.png")),
    BASE(6, new TileLoader("src/main/resources/base.png")),
    FLAG(7, new TileLoader("src/main/resources/flag.png")),
    PLAYER_BASE(8, new TileLoader("src/main/resources/player_base.png"));

    private final int number;
    private final TileLoader tileLoader;

    BackgroundPixelType(int number, TileLoader tile) {
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
        for (BackgroundPixelType tile : values()) {
            if (tile.getNumber() == number) {
                return tile.getTileLoader();
            }
        }
        throw new IllegalArgumentException("Invalid number: " + number);
    }
}
