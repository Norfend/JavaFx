package controllers;

import enums.Scene;
import launcher.MainFrame;
import models.view.MenuModel;
import services.Constants;
import views.MenuView;

import java.awt.*;
import java.util.logging.Level;


public class MenuController extends Control<MenuModel, MenuView> {
    public MenuController(MenuModel model) {
        this.model = model;
    }

    public Image getBackgroundImage() {
        return this.model.imageIcon().getImage();
    }

    public void continueGameButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        Constants.GAME = true;
        MainFrame.getInstance().goToCard(Scene.GAME);
    }

    public void newGameButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        Constants.GAME = true;
        MainFrame.getInstance().goToCard(Scene.GAME);
    }

    public void loadGameButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        MainFrame.getInstance().goToCard(Scene.LOAD);
    }

    public void settingsButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        MainFrame.getInstance().goToCard(Scene.SETTINGS);
    }

    public void helpButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        MainFrame.getInstance().goToCard(Scene.HELP);
    }

    public void exitButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        //add function for game saving before exit
        Constants.MAIN_LOOP = false;
    }

    public boolean isGame() {
        return Constants.PAUSE_GAMES;
    }

    public boolean isSavedGameIsAvailable() {
        return Constants.SAVES;
    }
}
