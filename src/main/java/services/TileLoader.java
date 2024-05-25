package services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;

public class TileLoader {
    private final BufferedImage tile;

    public TileLoader(String imagePath) {
        this.tile = loadImage(imagePath);
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        }
        catch (Exception e) {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.WARNING, "Error in loading of the tile\n" + e.getMessage());
            }
            throw new RuntimeException("Error in loading of the tile\n" + e.getMessage());
        }
    }

    public BufferedImage getTile() {
        return tile;
    }
}
