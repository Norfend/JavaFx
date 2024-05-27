package models.entities;

import enums.EntitiesType;
import enums.MoveType;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class for entities
 *
 * @author Egor Uporov
 */
public abstract class Entity {
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private final Point location;
    private final int entityWidth;
    private final int entityHeight;
    private final EntitiesType type;

    public Entity(Point location, EntitiesType type) {
        this.location = location;
        this.entityWidth = 32;
        this.entityHeight = 32;
        this.type = type;
    }

    public Point getLocation() {
        return this.location;
    }

    public EntitiesType getType() {
        return this.type;
    }

    public int getEntityWidth() {
        return entityWidth;
    }

    public int getEntityHeight() {
        return entityHeight;
    }

    /**
     * Moves the entity in the specified direction.
     * <p>
     * This method updates the location of the entity based on the specified movement
     * type. The entity is moved one unit in the specified direction: UP, DOWN, LEFT,
     * or RIGHT. If the movement type is NONE, the entity remains stationary.
     * </p>
     *
     * @param moveType the type of movement to perform (UP, DOWN, LEFT, RIGHT, or NONE)
     */
    public void move(MoveType moveType) {
        switch (moveType) {
            case UP: {
                location.y--;
                break;
            }
            case DOWN: {
                location.y++;
                break;
            }
            case LEFT: {
                location.x--;
                break;
            }
            case RIGHT: {
                location.x++;
                break;
            }
            case NONE: {
                break;
            }
            default: {
                LOGGER.log(Level.WARNING, "Error in Entity movement\n");
                break;
            }
        }
    }
}
