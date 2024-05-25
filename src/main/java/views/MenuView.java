package views;

import controllers.MenuController;
import services.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class MenuView extends View<MenuController> {
    private final JButton continueGameButton = new JButton("Continue Game");
    private final JButton newGameButton = new JButton("New Game");
    private final JButton loadGameButton = new JButton("Load Game");
    private final JButton settingsButton = new JButton("Settings");
    private final JButton helpButton = new JButton("Help");
    private final JButton exitButton = new JButton("Exit");

    public MenuView(MenuController controller) {
        this.controller = controller;
    }

    public void init() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        this.add(continueGameButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 150;
        this.add(newGameButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 150;
        this.add(loadGameButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 150;
        this.add(settingsButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipadx = 150;
        this.add(helpButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.ipadx = 150;
        this.add(exitButton, gbc);
        continueGameButton.addActionListener(e -> controller.continueGameButton());
        newGameButton.addActionListener(e -> controller.newGameButton());
        loadGameButton.addActionListener(e -> controller.loadGameButton());
        settingsButton.addActionListener(e -> controller.settingsButton());
        helpButton.addActionListener(e -> controller.helpButton());
        exitButton.addActionListener(e -> controller.exitButton());
        continueGameButton.setVisible(false);
        loadGameButton.setEnabled(controller.isSavedGameIsAvailable());
    }

    public void update() {
        continueGameButton.setVisible(controller.isGame());
        loadGameButton.setEnabled(controller.isSavedGameIsAvailable());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.controller.getBackgroundImage(), 250, 10, Constants.WIDTH / 2,
                Constants.HEIGHT / 2,  null);
    }
}
