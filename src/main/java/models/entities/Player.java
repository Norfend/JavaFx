package models.entities;

import enums.EntitiesType;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for player
 * Inventory always empties for new player
 *
 * @author Egor Uporov
 */
public class Player extends MoveableEntity {
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
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
        this.inventory = new LinkedList<>();
        this.visible = true;
    }

    /**
     * Adds an item to the inventory.
     * <p>
     * This method attempts to add the specified item to the inventory. If successful,
     * the item is added to the inventory list, and the method returns {@code true}.
     * If an exception occurs during the addition process, a warning message is logged,
     * and the method returns {@code false}.
     * </p>
     *
     * @param item the item to be added to the inventory
     * @return {@code true} if the item is successfully added to the inventory, {@code false} otherwise
     */
    public Boolean addItemToInventory(Items item) {
        try {
            this.inventory.add(item);
            return true;
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error in items adding to inventory\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes an item from the inventory.
     * <p>
     * This method attempts to delete the specified item from the inventory. If successful,
     * the item is removed from the inventory list, and the method returns {@code true}.
     * If an exception occurs during the deletion process, a warning message is logged,
     * and the method returns {@code false}.
     * </p>
     *
     * @param item the item to be deleted from the inventory
     * @return {@code true} if the item is successfully deleted from the inventory, {@code false} otherwise
     */
    public Boolean deleteItemFromInventory(Items item) {
        try {
            this.inventory.remove(item);
            return true;
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error in items deleting to inventory\n" + e.getMessage());
            return false;
        }
    }
}
