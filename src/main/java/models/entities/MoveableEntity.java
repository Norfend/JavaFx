package models.entities;

import enums.EntitiesType;
import enums.MoveType;

import java.awt.*;

public abstract class MoveableEntity extends Entity {
    private int ammunition;
    private int armor;
    private int attackStrength;
    private int health;
    private MoveType moveType;

    public MoveableEntity(Point location, int ammunition, int armor, int attackStrength, int health,
                          EntitiesType type) {
        super(location, type);
        this.ammunition = ammunition;
        this.armor = armor;
        this.attackStrength = attackStrength;
        this.health = health;
        this.moveType = MoveType.NONE;
    }

    public Bullet shoot(MoveType moveType) {
        return new Bullet(new Point(this.getLocation().x, this.getLocation().y), -1 * attackStrength, EntitiesType.BULLET, this.moveType);
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }
}
