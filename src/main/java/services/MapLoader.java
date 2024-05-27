package services;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapLoader {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private final int[][] map;
    private final ArrayList<Point> basesLocation = new ArrayList<>();
    private final ArrayList<Point> itemsLocation = new ArrayList<>();
    private Point playerBaseLocation;

    /**
     * Constructs a new {@code MapLoader} instance.
     * <p>
     * This constructor initializes the {@code MapLoader} with empty lists for
     * base locations and item locations. It sets up the necessary data structures
     * for loading and parsing map files.
     * </p>
     */
    public MapLoader(String fileName) {
        this.map = mapLoading(fileName);
    }

    private int[][] mapLoading(String fileName) {
        LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        int[][] loadedMap = new int[24][];
        int tileSize = 32;
        int index = 0;
        String filePath = Constants.MAP_FILE_PATH + fileName;
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String[] line = scanner.nextLine().split(" ");
                    if (line.length == 24) {
                        int[] row = new int[24];
                        int temporaryIndex = 0;
                        for (String str : line) {
                            if (0 <= Integer.parseInt(str) && Integer.parseInt(str) <= 8) {
                                row[temporaryIndex] = Integer.parseInt(str);
                                if (row[temporaryIndex] == 6)
                                    this.basesLocation.add(new Point(temporaryIndex*tileSize, index*tileSize));
                                if (row[temporaryIndex] == 7)
                                    this.itemsLocation.add(new Point(temporaryIndex*tileSize, index*tileSize));
                                if (row[temporaryIndex] == 8)
                                    this.playerBaseLocation = new Point(temporaryIndex*tileSize, index*tileSize);
                                temporaryIndex++;
                            }
                            else {
                                LOGGER.log(Level.WARNING, "Number in map row is out of bound\n");
                                throw new RuntimeException("Number in Map file is out of range\n");
                            }
                        }
                        loadedMap[index] = row;
                        index++;
                    }
                    else {
                        LOGGER.log(Level.WARNING, "Line.length in map file isn't equal to 24\n");
                        throw new RuntimeException("Row length in Map file is out of range\n");
                    }
                }
            }
            catch (FileNotFoundException e) {
                LOGGER.log(Level.SEVERE, e.getMessage() +  "\n");
            }
            return loadedMap;
        }
        else {
            LOGGER.log(Level.WARNING, "Error with loading map file\n");
            throw new RuntimeException("Error in mapLoading, file nor found\n");
        }
    }

    public int[][] getMap() {
        return map;
    }

    public ArrayList<Point> getBasesLocation() {
        return basesLocation;
    }

    public ArrayList<Point> getItemsLocation() {
        return itemsLocation;
    }

    public Point getPlayerBaseLocation() {
        return playerBaseLocation;
    }
}
