package controllers;

import enums.*;
import models.entities.*;
import models.view.GameModel;
import services.Constants;
import views.GameView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

public class GameController extends Control<GameModel, GameView> {
    private MoveType moveType = MoveType.NONE;
    private BufferedImage backgroundImage;
    private BufferedImage overlayImage;

    public GameController(GameModel gameModel) {
        this.model = gameModel;
    }

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
            this.backgroundImage = createBackgroundImage(this.model.getGameMap().getMap());
        }
        else {
            System.out.println();
        }
        this.overlayImage = createOverlayImage();
    }

    public void move() {
        if (this.moveType != MoveType.NONE) {
            this.model.getPlayer().setMoveType(this.moveType);
        }
        moveMoveableEntity(this.model.getPlayer(), this.moveType);
        for (Enemy enemy: this.model.getEntities()) {
            moveMoveableEntity(enemy, enemy.getMoveType());
        }
        for (Bullet bullet: this.model.getAmmunition()) {
            moveMoveableEntity(bullet, bullet.getMoveType());
        }
    }

    public void shooting() {
        this.model.getAmmunition().add(this.model.getPlayer().shoot(this.model.getPlayer().getMoveType()));
    }

    private void moveMoveableEntity(Entity entity, MoveType move) {
        if(checkCollision(entity, move)){
            entity.move(move);
        }
        else if(entity.getType() == EntitiesType.BULLET) {
            this.model.getAmmunition().remove(entity);
        }
        this.overlayImage = createOverlayImage();
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
                yield passableTiles.contains(gameMap[entityLocation.y / 32][entityLocation.x / 32]) &&
                        passableTiles.contains(gameMap[entityLocation.y / 32][(entityLocation.x + 31) / 32]);
            }
            case DOWN -> {
                entityLocation.y++;
                yield passableTiles.contains(gameMap[(entityLocation.y + 31) / 32][entityLocation.x / 32]) &&
                        passableTiles.contains(gameMap[(entityLocation.y + 31) / 32][(entityLocation.x + 31) / 32]);
            }
            case LEFT -> {
                entityLocation.x--;
                yield passableTiles.contains(gameMap[entityLocation.y / 32][entityLocation.x / 32]) &&
                        passableTiles.contains(gameMap[(entityLocation.y + 31) / 32][entityLocation.x / 32]);
            }
            case RIGHT -> {
                entityLocation.x++;
                yield passableTiles.contains(gameMap[entityLocation.y / 32][(entityLocation.x + 31) / 32]) &&
                        passableTiles.contains(gameMap[(entityLocation.y + 31) / 32][(entityLocation.x + 31) / 32]);
            }
            default -> false;
        };
    }

    private BufferedImage createBackgroundImage(int[][] inputPixelsArray) {
        BufferedImage image = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        for (int i = 0; i < inputPixelsArray.length; i++) {
            for (int j = 0; j < inputPixelsArray[0].length; j++) {
                g2d.drawImage(drawBackgroundCell(inputPixelsArray[i][j]), j*32, i*32, null);
            }
        }
        return image;
    }

    private BufferedImage createOverlayImage() {
        BufferedImage image = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        BufferedImage bufferedImage;
        for (Enemy enemy: this.model.getEntities()) {
            bufferedImage = drawOverlayCell(enemy.getType().getNumber());
            bufferedImage = drawRotatedImage(bufferedImage, enemy.getMoveType().getAngle());
            g2d.drawImage(bufferedImage, enemy.getLocation().x, enemy.getLocation().y, null);
        }
        for (Bullet bullet: this.model.getAmmunition()){
            bufferedImage = drawOverlayCell(bullet.getType().getNumber());
            bufferedImage = drawRotatedImage(bufferedImage, bullet.getMoveType().getAngle());
            g2d.drawImage(bufferedImage, bullet.getLocation().x, bullet.getLocation().y, null);
        }
        for (Items items: this.model.getItems()) {
            g2d.drawImage(drawOverlayCell(items.getType().getNumber()), items.getLocation().x, items.getLocation().y,
                    null);
        }
        bufferedImage = drawOverlayCell(this.model.getPlayer().getType().getNumber());
        bufferedImage = drawRotatedImage(bufferedImage, this.model.getPlayer().getMoveType().getAngle());
        g2d.drawImage(bufferedImage, this.model.getPlayer().getLocation().x, this.model.getPlayer().getLocation().y,
                null);
        g2d.dispose();
        return image;
    }

    private BufferedImage drawBackgroundCell(int value) {
        return switch (value) {
            case 0 -> getBackgroundTile(0);
            case 1 -> getBackgroundTile(1);
            case 2 -> getBackgroundTile(2);
            case 3 -> getBackgroundTile(3);
            case 4 -> getBackgroundTile(4);
            case 5 -> getBackgroundTile(5);
            case 6 -> getBackgroundTile(6);
            case 7 -> getBackgroundTile(7);
            case 8 -> getBackgroundTile(8);
            default -> {
                if (Constants.LOGGING) {
                    Constants.LOGGER.log(Level.WARNING, "Error in background image creation\n");
                }
                throw new RuntimeException("Error in loading of the background tile\n");
            }
        };
    }

    private BufferedImage drawOverlayCell(int value) {
        return switch (value) {
            case 1 -> getOverlayTile(1);
            case 2 -> getOverlayTile(2);
            case 4 -> getOverlayTile(4);
            case 5 -> getOverlayTile(5);
            default -> {
                if (Constants.LOGGING) {
                    Constants.LOGGER.log(Level.WARNING, "Error in background image creation\n");
                }
                throw new RuntimeException("Error in loading of the overlay tile\n");
            }
        };
    }

    private BufferedImage drawRotatedImage(BufferedImage image, int angle) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage rotatedImage;
        Graphics2D g2d;
        switch (angle) {
            case 90: {
                rotatedImage = new BufferedImage(height, width, image.getType());
                g2d = rotatedImage.createGraphics();
                g2d.translate((height - width) / 2, (width - height) / 2);
                g2d.rotate(Math.toRadians(90), width / 2.0, height / 2.0);
                break;
            }
            case 180: {
                rotatedImage = new BufferedImage(width, height, image.getType());
                g2d = rotatedImage.createGraphics();
                g2d.translate(width, height);
                g2d.rotate(Math.toRadians(180), 0, 0);
                break;
            }
            case 270: {
                rotatedImage = new BufferedImage(height, width, image.getType());
                g2d = rotatedImage.createGraphics();
                g2d.translate((height - width) / 2, (width - height) / 2);
                g2d.rotate(Math.toRadians(270), width / 2.0, height / 2.0);
                break;
            }
            default: {
                rotatedImage = new BufferedImage(width, height, image.getType());
                g2d = rotatedImage.createGraphics();
                break;
                }
        }
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
    }

    public BufferedImage getBackgroundTile(int tileNumber) {
        return BackgroundPixelType.getTileByNumber(tileNumber).getTile();
    }

    public BufferedImage getOverlayTile(int tileNumber) {
        return OverlayPixelType.getTileByNumber(tileNumber).getTile();
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
}
