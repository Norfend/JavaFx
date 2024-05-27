package controllers;

import enums.Scene;
import launcher.MainFrame;
import models.view.MenuModel;
import services.Constants;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuController extends Control<MenuModel> {
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    public MenuController(MenuModel model) {
        this.model = model;
    }

    /**
     * Retrieves the background image of the menu screen.
     *
     * @return the background image of the menu screen
     */
    public Image getBackgroundImage() {
        return this.model.imageIcon().getImage();
    }

    /**
     * Handles the action when the "Continue Game" button is clicked.
     * Activates the game loop and switches to the game screen.
     */
    public void continueGameButton() {
        LOGGER.log(Level.INFO, "continueGameButton function of MenuController has been called\n");
        Constants.GAME = true;
        MainFrame.getInstance().goToCard(Scene.GAME);
    }

    /**
     * Handles the action when the "New Game" button is clicked.
     * Activates the game loop and switches to the game screen.
     */
    public void newGameButton() {
        LOGGER.log(Level.INFO, "newGameButton function of MenuController has been called\n");
        Constants.GAME = true;
        MainFrame.getInstance().goToCard(Scene.GAME);
    }

    /**
     * Handles the action when the "Load Game" button is clicked.
     * Switches to the load game screen.
     */
    public void loadGameButton() {
        LOGGER.log(Level.INFO, "loadGameButton function of MenuController has been called\n");
        MainFrame.getInstance().goToCard(Scene.LOAD);
    }

    /**
     * Handles the action when the "Settings" button is clicked.
     * Switches to the settings screen.
     */
    public void loggingButton() {
        LOGGER.log(Level.INFO, "loggingButton function of MenuController has been called\n");
        MainFrame.getInstance().goToCard(Scene.SETTINGS);
    }

    /**
     * Handles the action when the "Help" button is clicked.
     * Switches to the help screen.
     */
    public void helpButton() {
        LOGGER.log(Level.INFO, "helpButton function of MenuController has been called\n");
        MainFrame.getInstance().goToCard(Scene.HELP);
    }

    /**
     * Handles the action when the "Exit" button is clicked.
     * Exits the application.
     */
    public void exitButton() {
        LOGGER.log(Level.INFO, "exitButton function of MenuController has been called\n");
        // add function for game saving before exit
        Constants.MAIN_LOOP = false;
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean isGame() {
        return Constants.PAUSE_GAMES;
    }

    /**
     * Checks if a saved game is available.
     *
     * @return true if a saved game is available, false otherwise
     */
    public boolean isSavedGameIsAvailable() {
        return Constants.SAVES;
    }

    /**
     * Checks if the main loop is active.
     *
     * @return true if the main loop is active, false otherwise
     */
    public boolean isMainLoop() {
        return Constants.MAIN_LOOP;
    }

    /**
     * Checks if the game loop is active.
     *
     * @return true if the game loop is active, false otherwise
     */
    public boolean isGameLoop() {
        return Constants.GAME;
    }
}
