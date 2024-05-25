package models.entities;

import enums.EntitiesType;
import enums.MoveType;

import java.awt.*;

public class Bullet extends Items{
    private final MoveType moveType;

    public Bullet(Point position, Integer bonus, EntitiesType type, MoveType moveType) {
        super(position, bonus, type);
        this.moveType = moveType;
    }

    public MoveType getMoveType() {
        return moveType;
    }
}
