package models.entities;

import enums.EntitiesType;
import services.Node;

import java.awt.*;
import java.util.List;

public class Enemy extends MoveableEntity {
    private List<Node> path;

    public Enemy(Point location, int ammunition, int armor, int attackStrength, int health, EntitiesType type) {
        super(location, ammunition, armor, attackStrength, health, type);
    }

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }
}
