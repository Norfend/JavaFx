package launcher;

import controllers.GameController;
import controllers.HelpController;
import controllers.LoadController;
import controllers.MenuController;
import java.io.File;

import models.view.GameModel;
import models.view.HelpModel;
import models.view.LoadModel;
import models.view.MenuModel;
import services.Constants;
import services.CustomFormatter;
import views.GameView;
import views.HelpView;
import views.LoadView;
import views.MenuView;

import javax.swing.*;
import java.io.FilenameFilter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Launcher {
    private static final MainFrame mainFrame = MainFrame.getInstance();

    public static void main(String[] args) {
        final long uiFPS = Constants.NANOSECONDS / Constants.UI_FRAME_RATE;
        final long gameFPS = Constants.NANOSECONDS / Constants.GAME_FRAME_RATE;
        long lastLoopTime = System.nanoTime();
        long currentTime;
        long deltaTime;
        handlerInitialization();
        bootMainFrame();
        while (Constants.MAIN_LOOP) {
            long lastLoopGameTime = System.nanoTime();
            long currentGameTime;
            long deltaGameTime;
            currentTime = System.nanoTime();
            deltaTime = currentTime - lastLoopTime;
            lastLoopTime = currentTime;
            long sleepTime = (uiFPS - deltaTime) / 1000000;
            while (Constants.GAME) {
                currentGameTime = System.nanoTime();
                deltaGameTime = currentGameTime - lastLoopGameTime;
                lastLoopGameTime = currentGameTime;
                long sleepGameTime = (gameFPS - deltaGameTime) / 1000000;
                if (sleepGameTime > 0) {
                    try {
                        Thread.sleep(sleepGameTime);
                    } catch (InterruptedException e) {
                        Constants.LOGGER.log(Level.SEVERE, "Game while cycle was interrupted!");
                    }
                }
                mainFrame.updateUserInterface();
            }
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Constants.LOGGER.log(Level.SEVERE, "UI while cycle was interrupted!");
                }
            }
            mainFrame.updateUserInterface();
        }
        System.exit(0);
    }

    public static void bootMainFrame() {
        Constants.SAVES = checkSavedGames();
        //game initialization
        GameModel gameModel = new GameModel("MAP_LVL0.txt");
        GameController gameController = new GameController(gameModel);
        GameView gameView = new GameView(gameController);
        gameView.init();
        //menu initialization
        MenuModel menuModel = new MenuModel(new ImageIcon("src/main/resources/background.png"));
        MenuController menuController = new MenuController(menuModel);
        MenuView menuView =  new MenuView(menuController);
        menuView.init();
        //help initialization
        HelpModel helpModel = new HelpModel(new ImageIcon("src/main/resources/help.png"));
        HelpController helpController = new HelpController(helpModel);
        HelpView helpView = new HelpView(helpController);
        helpView.init();
        //load initialization
        LoadModel loadModel = new LoadModel();
        LoadController loadController = new LoadController(loadModel);
        LoadView loadView = new LoadView(loadController);
        loadView.init();
        // inicializace MainFrame
        mainFrame.setGameView(gameView);
        mainFrame.setMenuView(menuView);
        mainFrame.setHelpView(helpView);
        mainFrame.setLoadView(loadView);
        mainFrame.init();
    }

    private static void handlerInitialization() {
        for (Handler handler : Constants.LOGGER.getHandlers()) {
            Constants.LOGGER.removeHandler(handler);
        }
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new CustomFormatter());
        Constants.LOGGER.addHandler(consoleHandler);
    }

    private static boolean checkSavedGames() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        File folder = new File(Constants.SAVE_FILE_PATH);
        if (folder.exists() && folder.isDirectory()) {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.INFO, "Directory for game saves was founded\n");
            }
            FilenameFilter pjvFilter = (dir, name) -> name.endsWith(".pjv");
            File[] pjvFiles = folder.listFiles(pjvFilter);
            if (pjvFiles == null) {
                if (Constants.LOGGING) {
                    Constants.LOGGER.log(Level.WARNING,"An error occurred while listing saves\n");
                }
                return false;
            }
            else {
                return pjvFiles.length > 0;
            }
        }
        else {
            if (folder.mkdir()) {
                if (Constants.LOGGING) {
                    Constants.LOGGER.log(Level.INFO, "Directory for game saving was created\n");
                }
            }
            else {
                if (Constants.LOGGING) {
                    Constants.LOGGER.log(Level.SEVERE, "Directory for game saving wasn't created\n");
                }
            }
        }
        return false;
    }
}
