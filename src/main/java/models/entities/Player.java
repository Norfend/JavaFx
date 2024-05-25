package models.entities;

import enums.EntitiesType;
import services.Constants;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

/**
 * A class for player
 * Inventory always empties for new player
 *
 * @author Egor Uporov
 */
public class Player extends MoveableEntity {
    private final List<Items> inventory;
    private final Boolean visible;

    /**
     * Constructor for players
     *
     * @param location Gets Point for initial position of player
     *
     * @param ammunition Gets int for the initial number of shells
     *
     * @param armor Gets int for the initial number of armors
     *
     * @param attackStrength Gets int for the initial amount of attack strength
     *
     * @param health Gets int for the initial amount of health
     *
     * @param type Gets EntitiesType for future using
     */
    public Player(Point location, int ammunition, int armor, int attackStrength, int health, EntitiesType type) {
        super(location, ammunition, armor, attackStrength, health, type);
        this.inventory = new LinkedList<Items>();
        this.visible = true;
    }

    public Boolean addItemToInventory(Items item) {
        try {
            this.inventory.add(item);
            return true;
        }
        catch (Exception e) {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.WARNING, "Error in items adding to inventory\n" + e.getMessage());
            }
            return false;
        }
    }

    public Boolean deleteItemFromInventory(Items item) {
        try {
            this.inventory.remove(item);
            return true;
        }
        catch (Exception e) {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.WARNING, "Error in items deleting to inventory\n" + e.getMessage());
            }
            return false;
        }
    }
}
