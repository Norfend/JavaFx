package models.view;

import models.entities.Bullet;
import models.entities.Enemy;
import models.entities.Items;
import models.entities.Player;
import services.MapLoader;

import java.util.LinkedList;
import java.util.List;

public class GameModel {
    private final MapLoader gameMap;
    private List<Items> items;
    private final List<Bullet> ammunition;
    private List<Enemy> entities;
    private Player player;

    public GameModel (String filePath) {
        this.ammunition = new LinkedList<>();
        this.gameMap = new MapLoader(filePath);
        this.items = new LinkedList<>();
        this.entities = new LinkedList<>();
    }

    public MapLoader getGameMap() {
        return gameMap;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public List<Bullet> getAmmunition() {
        return ammunition;
    }

    public List<Enemy> getEntities() {
        return entities;
    }

    public void setEntities(List<Enemy> entities) {
        this.entities = entities;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
