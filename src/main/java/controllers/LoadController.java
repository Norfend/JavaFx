package controllers;

import enums.Scene;
import launcher.MainFrame;
import models.view.LoadModel;
import services.Constants;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.logging.Level;

public class LoadController extends Control<LoadModel> {
    private String selectedSave;
    public LoadController(LoadModel loadModel) {
        this.model = loadModel;
    }

    /**
     * Loads the selected game from the list of saved games.
     * <p>
     * This method sets the path to the selected saved game file, triggers the loading process, and switches to the menu screen.
     * It logs information about the execution of the method.
     * </p>
     */
    public void loadGameButton() {
        LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        Constants.LOAD_GAMES = true;
        Constants.PATH_TO_LOADED_FILE = selectedSave;
        MainFrame.getInstance().goToCard(Scene.MENU);
    }

    /**
     * Sets the selected save file.
     * <p>
     * This method sets the selected save file based on user input.
     * </p>
     *
     * @param inputString the name of the selected save file
     */
    public void jListSelection(String inputString) {
        if (inputString != null) this.selectedSave = inputString;
    }

    public ArrayList<String> getSaveFiles() {
        return this.model.getSaveFiles();
    }

    /**
     * Sets the list of available save files in the model.
     * <p>
     * This method retrieves the list of save files from the specified save file path.
     * It logs information about the execution of the method.
     * </p>
     */
    public void setSaveFiles() {
        File folder = new File(Constants.SAVE_FILE_PATH);
        ArrayList<String> result = new ArrayList<>();
        LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        if (folder.exists() && folder.isDirectory()) {
            fileFilter(folder, result);
        }
        else {
            LOGGER.log(Level.WARNING,"An error occurred while searching for a folder with saves\n");
            model.setSaveFiles(result);
        }
    }

    /**
     * Updates the list of available save files in the model.
     * <p>
     * This method updates the list of save files from the specified save file path.
     * </p>
     */
    public void updateSaveFiles() {
        File folder = new File(Constants.SAVE_FILE_PATH);
        ArrayList<String> result = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            fileFilter(folder, result);
        }
        else {
            LOGGER.log(Level.WARNING,"An error occurred while searching for a folder with saves\n");
            this.model.setSaveFiles(result);
        }
    }

    private void fileFilter(File folder, ArrayList<String> result) {
        FilenameFilter pjvFilter = (dir, name) -> name.endsWith(".pjv");
        File[] pjvFiles = folder.listFiles(pjvFilter);
        if (pjvFiles == null) {
            LOGGER.log(Level.WARNING,"An error occurred while listing saves\n");
        }
        else {
            for (File file: pjvFiles) {
                result.add(file.getName());
            }
        }
        this.model.setSaveFiles(result);
    }
}
