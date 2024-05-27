package services;

import enums.OverlayPixelType;
import models.entities.Bullet;
import models.entities.Enemy;
import models.entities.Items;
import models.view.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class createOverlayImage {
    protected static final Logger LOGGER = Logger.getLogger("createOverlayImage");

    private createOverlayImage() {}

    /**
     * Creates a BufferedImage representing the current state of the game.
     * <p>
     * This method generates an image that visually represents the game state based
     * on the provided {@code GameModel}. It draws entities, bullets, items, and the
     * player onto the image. Each entity and bullet is drawn with its corresponding
     * appearance and rotation angle. The resulting image is returned.
     * </p>
     *
     * @param gameModel the {@code GameModel} containing the current state of the game
     * @return a BufferedImage representing the current game state
     */
    public static BufferedImage createImage(GameModel gameModel) {
        BufferedImage image = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        BufferedImage bufferedImage;
        for (Enemy enemy: gameModel.getEntities()) {
            bufferedImage = drawOverlayCell(enemy.getType().getNumber());
            bufferedImage = drawRotatedImage(bufferedImage, enemy.getMoveType().getAngle());
            g2d.drawImage(bufferedImage, enemy.getLocation().x, enemy.getLocation().y, null);
        }
        for (Bullet bullet: gameModel.getAmmunition()){
            bufferedImage = drawOverlayCell(bullet.getType().getNumber());
            bufferedImage = drawRotatedImage(bufferedImage, bullet.getMoveType().getAngle());
            g2d.drawImage(bufferedImage, bullet.getLocation().x, bullet.getLocation().y, null);
        }
        for (Items items: gameModel.getItems()) {
            g2d.drawImage(drawOverlayCell(items.getType().getNumber()), items.getLocation().x, items.getLocation().y,
                    null);
        }
        bufferedImage = drawOverlayCell(gameModel.getPlayer().getType().getNumber());
        bufferedImage = drawRotatedImage(bufferedImage, gameModel.getPlayer().getMoveType().getAngle());
        g2d.drawImage(bufferedImage, gameModel.getPlayer().getLocation().x, gameModel.getPlayer().getLocation().y,
                null);
        g2d.dispose();
        return image;
    }

    private static BufferedImage drawRotatedImage(BufferedImage image, int angle) {
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

    private static BufferedImage drawOverlayCell(int value) {
        return switch (value) {
            case 1 -> getOverlayTile(1);
            case 2 -> getOverlayTile(2);
            case 4 -> getOverlayTile(4);
            case 5 -> getOverlayTile(5);
            default -> {
                LOGGER.log(Level.WARNING, "Error in background image creation\n");
                throw new RuntimeException("Error in loading of the overlay tile\n");
            }
        };
    }

    private static BufferedImage getOverlayTile(int tileNumber) {
        return OverlayPixelType.getTileByNumber(tileNumber).getTile();
    }
}
