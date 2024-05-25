package enums;

/**
 * ENUM for entities moving type
 * <p>
 * Available types: LEFT, RIGHT, UP, DOWN
 */
public enum MoveType {
    LEFT(270),
    RIGHT(90),
    UP(0),
    DOWN(180),
    NONE(0);

    private final int angle;

    MoveType(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }
}
