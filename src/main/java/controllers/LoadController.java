package controllers;

import enums.Scene;
import launcher.MainFrame;
import models.view.LoadModel;
import services.Constants;
import views.LoadView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.logging.Level;

public class LoadController extends Control<LoadModel, LoadView> {
    private String selectedSave;
    public LoadController(LoadModel loadModel) {
        this.model = loadModel;
    }

    public void loadGameButton() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        Constants.LOAD_GAMES = true;
        Constants.PATH_TO_LOADED_FILE = selectedSave;
        MainFrame.getInstance().goToCard(Scene.MENU);
    }

    public void jListSelection(String inputString) {
        if (inputString != null) this.selectedSave = inputString;
    }

    public ArrayList<String> getSaveFiles() {
        return this.model.getSaveFiles();
    }

    public void setSaveFiles() {
        File folder = new File(Constants.SAVE_FILE_PATH);
        ArrayList<String> result = new ArrayList<>();
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        if (folder.exists() && folder.isDirectory()) {
            fileFilter(folder, result);
        }
        else {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.WARNING,"An error occurred while searching for a folder with saves\n");
            }
            model.setSaveFiles(result);
        }
    }

    public void updateSaveFiles() {
        File folder = new File(Constants.SAVE_FILE_PATH);
        ArrayList<String> result = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            fileFilter(folder, result);
        }
        else {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.WARNING,"An error occurred while searching for a folder with saves\n");
            }
            this.model.setSaveFiles(result);
        }
    }

    private void fileFilter(File folder, ArrayList<String> result) {
        FilenameFilter pjvFilter = (dir, name) -> name.endsWith(".pjv");
        File[] pjvFiles = folder.listFiles(pjvFilter);
        if (pjvFiles == null) {
            if (Constants.LOGGING) {
                Constants.LOGGER.log(Level.WARNING,"An error occurred while listing saves\n");
            }
        }
        else {
            for (File file: pjvFiles) {
                result.add(file.getName());
            }
        }
        this.model.setSaveFiles(result);
    }
}
