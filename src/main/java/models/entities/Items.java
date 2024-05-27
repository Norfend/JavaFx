package models.entities;

import enums.EntitiesType;

import java.awt.*;

public class Items extends Entity {
    private final Integer bonus;

    public Items(Point position, Integer bonus, EntitiesType type) {
        super(position, type);
        this.bonus = bonus;
    }

    public Integer getBonus() {
        return bonus;
    }
}
