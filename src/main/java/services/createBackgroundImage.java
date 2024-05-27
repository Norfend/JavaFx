package services;

import enums.BackgroundPixelType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class createBackgroundImage {
    protected static final Logger LOGGER = Logger.getLogger("createBackgroundImage");

    private createBackgroundImage() {}

    /**
     * Creates a BufferedImage from a 2D array of pixel values.
     * <p>
     * This method generates an image from the provided 2D array of pixel values,
     * where each pixel value corresponds to a color. The resulting image is returned.
     * </p>
     *
     * @param inputPixelsArray a 2D array representing pixel values, where each
     *                         value corresponds to a color
     * @return a BufferedImage created from the input pixel array
     */
    public static BufferedImage createImage(int[][] inputPixelsArray) {
        BufferedImage image = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        for (int i = 0; i < inputPixelsArray.length; i++) {
            for (int j = 0; j < inputPixelsArray[0].length; j++) {
                g2d.drawImage(drawBackgroundCell(inputPixelsArray[i][j]), j*32, i*32, null);
            }
        }
        return image;
    }

    private static BufferedImage drawBackgroundCell(int value) {
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
                LOGGER.log(Level.WARNING, "Error in background image creation\n");
                throw new RuntimeException("Error in loading of the background tile\n");
            }
        };
    }

    private static BufferedImage getBackgroundTile(int tileNumber) {
        return BackgroundPixelType.getTileByNumber(tileNumber).getTile();
    }
}
