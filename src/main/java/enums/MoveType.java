package enums;

/**
 * Enumeration of movement directions.
 * <p>
 * This enum defines different movement directions, such as left, right, up, down,
 * and none. Each movement direction is associated with a specific angle, which
 * represents the direction in degrees.
 * </p>
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
