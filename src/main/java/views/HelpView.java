package views;

import controllers.HelpController;
import enums.Scene;
import launcher.MainFrame;
import services.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;

public class HelpView extends View<HelpController> implements KeyListener {
    public HelpView(HelpController helpController) {
        this.controller = helpController;
    }

    public void init() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        setFocusable(true);
        addKeyListener(this);
    }

    public void update() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.controller.getBackgroundImage(), 400, 20, Constants.WIDTH / 2,
                Constants.HEIGHT / 2,  null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            MainFrame.getInstance().goToCard(Scene.MENU);
        }
    }
}
