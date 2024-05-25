package models.entities;

import enums.EntitiesType;
import enums.MoveType;
import services.Constants;

import java.awt.*;
import java.util.logging.Level;

/**
 * An abstract class for entities
 *
 * @author Egor Uporov
 */
public abstract class Entity {
    private Point location;
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

    public void setLocation(Point location) {
        this.location = location;
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
     * Function for entities moving
     *
     * @param moveType Get ENUM to determine the direction of movement
     *
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
                if (Constants.LOGGING) {
                    Constants.LOGGER.log(Level.WARNING, "Error in Entity movement\n");
                }
                break;
            }
        }
    }
}
