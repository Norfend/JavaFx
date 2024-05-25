package models.entities;

import enums.EntitiesType;
import enums.MoveType;

import java.awt.*;

public class Enemy extends MoveableEntity {

    public Enemy(Point location, int ammunition, int armor, int attackStrength, int health, EntitiesType type) {
        super(location, ammunition, armor, attackStrength, health, type);
    }
}
