package services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileLoader {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private final BufferedImage tile;

    public TileLoader(String imagePath) {
        this.tile = loadImage(imagePath);
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error in loading of the tile\n" + e.getMessage());
            throw new RuntimeException("Error in loading of the tile\n" + e.getMessage());
        }
    }

    public BufferedImage getTile() {
        return tile;
    }
}
