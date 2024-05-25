package views;

import controllers.GameController;
import enums.MoveType;
import enums.Scene;
import launcher.MainFrame;
import services.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;

public class GameView extends View<GameController> implements KeyListener {

    public GameView(GameController gameController) {
        this.controller = gameController;
    }

    public void init() {
        if (Constants.LOGGING) {
            Constants.LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        }
        setFocusable(true);
        addKeyListener(this);
        controller.modelInitialization();
    }

    public void update() {
        this.controller.move();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g2d);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        g2d.drawImage(this.controller.getBackgroundImage(), 0, 0, null);
        g2d.setComposite(ac);
        g2d.drawImage(this.controller.getOverlayImage(), 0, 0, null);
        g2d.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                this.controller.setMoveType(MoveType.UP);
                break;
            case KeyEvent.VK_S:
                this.controller.setMoveType(MoveType.DOWN);
                break;
            case KeyEvent.VK_A:
                this.controller.setMoveType(MoveType.LEFT);
                break;
            case KeyEvent.VK_D:
                this.controller.setMoveType(MoveType.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                this.controller.shooting();
                break;
            case KeyEvent.VK_ESCAPE: {
                Constants.GAME = false;
                Constants.PAUSE_GAMES = true;
                MainFrame.getInstance().goToCard(Scene.MENU);
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.setMoveType(MoveType.NONE);
    }
}
