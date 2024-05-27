package controllers;

import enums.*;
import models.entities.*;
import models.view.GameModel;
import services.Constants;
import services.Node;
import services.createBackgroundImage;
import services.createOverlayImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static services.AStarPathfinding.findPath;

public class GameController extends Control<GameModel> {
    private MoveType moveType = MoveType.NONE;
    private BufferedImage backgroundImage;
    private BufferedImage overlayImage;

    public GameController(GameModel gameModel) {
        this.model = gameModel;
    }

    /**
     * Initializes the model with default or loaded data.
     * <p>
     * This method initializes the model with default data if no file path to loaded data is provided.
     * It creates a player, enemies, and items based on the map data and sets them in the model.
     * Additionally, it generates the background and overlay images for the model.
     * </p>
     */
    public void modelInitialization() {
        if(Constants.PATH_TO_LOADED_FILE.isBlank()) {
            List<Enemy> enemies = new LinkedList<>();
            List<Items> items = new LinkedList<>();
            this.model.setPlayer(new Player(this.model.getGameMap().getPlayerBaseLocation(), 10,
                    0, 30, 90, EntitiesType.PLAYER));
            for (var location: this.model.getGameMap().getBasesLocation()) {
                enemies.add(new Enemy(location, -1, 90, 30, 90, EntitiesType.NPC));
            }
            this.model.setEntities(enemies);
            for (var item: this.model.getGameMap().getItemsLocation()) {
                items.add(new Items(item, 30, EntitiesType.ARMOR));
            }
            this.model.setItems(items);
            this.backgroundImage = createBackgroundImage.createImage(this.model.getGameMap().getMap());
        }
        else {
            System.out.println();
        }
        this.overlayImage = createOverlayImage.createImage(this.model);
    }

    /**
     * Moves entities and manages their behavior.
     * <p>
     * This method moves the player, enemies, and bullets according to their current movement types.
     * It also updates the path and movement type of enemies if a path to the player is found.
     * </p>
     */
    public void move() {
        if (this.moveType != MoveType.NONE) {
            this.model.getPlayer().setMoveType(this.moveType);
        }
        moveMoveableEntity(this.model.getPlayer(), this.moveType);
        for (Enemy enemy: this.model.getEntities()) {
            Node enemyNode = new Node(enemy.getLocation().x / enemy.getEntityWidth(),
                    enemy.getLocation().y / enemy.getEntityHeight(), true);
            Node playerNode = new Node(this.model.getPlayer().getLocation().x / this.model.getPlayer().getEntityWidth(),
                    this.model.getPlayer().getLocation().y / this.model.getPlayer().getEntityHeight(), true);
            List<Node> path = findRoute(enemyNode, playerNode);
            if (path != null) {
                enemy.setPath(path);
                chooseMoveType(enemy);
            }
            moveMoveableEntity(enemy, enemy.getMoveType());
        }
        for (Bullet bullet: this.model.getAmmunition()) {
            moveMoveableEntity(bullet, bullet.getMoveType());
        }
    }

    /**
     * Initiates shooting action for the player.
     * <p>
     * This method creates a new bullet and adds it to the list of ammunition in the model.
     * </p>
     */
    public void shooting() {
        this.model.getAmmunition().add(this.model.getPlayer().shoot());
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public BufferedImage getOverlayImage() {
        return overlayImage;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    private void chooseMoveType(Enemy enemy) {
        if (enemy.getPath().size() > 1) {
            enemy.getPath().removeFirst();
        }
        Point startLocation = enemy.getLocation();
        Point endLocation = new Point(enemy.getPath().getFirst().getX() * enemy.getEntityWidth(),
                enemy.getPath().getFirst().getY() * enemy.getEntityHeight());
        if (startLocation.x - endLocation.x < 0) {
            enemy.setMoveType(MoveType.RIGHT);
        }
        else if (startLocation.x - endLocation.x > 0) {
            enemy.setMoveType(MoveType.LEFT);
        }
        else if (startLocation.y - endLocation.y < 0) {
            enemy.setMoveType(MoveType.DOWN);
        }
        else if (startLocation.y - endLocation.y > 0) {
            enemy.setMoveType(MoveType.UP);
        }
        else {
            enemy.setMoveType(MoveType.NONE);
        }
    }

    private void moveMoveableEntity(Entity entity, MoveType move) {
        if(checkCollision(entity, move)){
            entity.move(move);
        }
        else if(entity.getType() == EntitiesType.BULLET) {
            this.model.getAmmunition().remove((Bullet) entity);
        }
        this.overlayImage = createOverlayImage.createImage(this.model);
    }

    private boolean checkCollision(Entity entity, MoveType move) {
        List<Integer> passableTiles;
        if(entity.getType() == EntitiesType.BULLET) {
            passableTiles = Arrays.asList(0, 4, 5, 6, 7, 8);
        }
        else {
            passableTiles = Arrays.asList(0, 4, 6, 7, 8);
        }
        Point entityLocation = new Point(entity.getLocation().x, entity.getLocation().y);
        int[][] gameMap = this.model.getGameMap().getMap();
        return switch (move) {
            case UP -> {
                entityLocation.y--;
                yield passableTiles.contains(gameMap[entityLocation.y / entity.getEntityWidth()][entityLocation.x / entity.getEntityHeight()]) &&
                        passableTiles.contains(gameMap[entityLocation.y / entity.getEntityWidth()][(entityLocation.x + 31) / entity.getEntityHeight()]);
            }
            case DOWN -> {
                entityLocation.y++;
                yield passableTiles.contains(gameMap[(entityLocation.y + 31) / entity.getEntityWidth()][entityLocation.x / entity.getEntityHeight()]) &&
                        passableTiles.contains(gameMap[(entityLocation.y + 31) / entity.getEntityWidth()][(entityLocation.x + 31) / entity.getEntityHeight()]);
            }
            case LEFT -> {
                entityLocation.x--;
                yield passableTiles.contains(gameMap[entityLocation.y / entity.getEntityWidth()][entityLocation.x / entity.getEntityHeight()]) &&
                        passableTiles.contains(gameMap[(entityLocation.y + 31) / entity.getEntityWidth()][entityLocation.x / entity.getEntityHeight()]);
            }
            case RIGHT -> {
                entityLocation.x++;
                yield passableTiles.contains(gameMap[entityLocation.y / entity.getEntityWidth()][(entityLocation.x + 31) / entity.getEntityHeight()]) &&
                        passableTiles.contains(gameMap[(entityLocation.y + 31) / entity.getEntityWidth()][(entityLocation.x + 31) / entity.getEntityHeight()]);
            }
            default -> false;
        };
    }

    private List<Node> findRoute(Node startNode, Node endNode) {
        Node[][] nodesMap = new Node[this.model.getGameMap().getMap().length][this.model.getGameMap().getMap()[0].length];
        List<Integer> passableTiles = Arrays.asList(0, 4, 6, 7, 8);
        for (int i = 0; i < this.model.getGameMap().getMap().length; i++) {
            for (int j = 0; j < this.model.getGameMap().getMap()[0].length; j++) {
                if (passableTiles.contains(this.model.getGameMap().getMap()[i][j])) {
                    nodesMap[i][j] = new Node(j, i, true);
                }
                else {
                    nodesMap[i][j] = new Node(j, i, false);
                }
            }
        }
        return findPath(startNode, endNode, nodesMap);
    }
}
