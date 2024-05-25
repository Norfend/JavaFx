package services;

import java.util.logging.Logger;

public class Constants {
    public static boolean LOGGING = true;
    public static boolean MAIN_LOOP = true;
    public static boolean GAME = false;
    public static boolean SAVES = false;
    public static boolean LOAD_GAMES = false;
    public static boolean PAUSE_GAMES = false;
    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;
    public final static int NANOSECONDS = 1000000000;
    public final static int UI_FRAME_RATE = 15;
    public final static int GAME_FRAME_RATE = 60;
    public static final Logger LOGGER = Logger.getLogger("");
    public final static String SAVE_FILE_PATH = "saves";
    public final static String MAP_FILE_PATH = "maps/";
    public static String PATH_TO_LOADED_FILE = "";

    private Constants(){}
}
