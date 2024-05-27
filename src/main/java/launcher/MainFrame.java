package launcher;

import enums.Scene;
import services.Constants;
import views.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final MainFrame INSTANCE = new MainFrame();
    private GameView gameView;
    private MenuView menuView;
    private LoadView loadView;
    private HelpView helpView;
    private CardLayout cardLayout;
    private JPanel cards;
    private View activeView;

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return INSTANCE;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public void setHelpView(HelpView helpView) {
        this.helpView = helpView;
    }

    public void setLoadView(LoadView loadView) {
        this.loadView = loadView;
    }

    public void init() {
        // inicializace panelu
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // inicializace komponent
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(gameView, Scene.GAME.toString());
        cards.add(menuView, Scene.MENU.toString());
        cards.add(helpView, Scene.HELP.toString());
        cards.add(loadView, Scene.LOAD.toString());
        this.add(cards);
        goToCard(Scene.MENU);
        this.setVisible(true);
    }

    public void updateUserInterface() {
        this.activeView.update();
    }

    public void goToCard(Scene scene) {
        switch (scene) {
            case GAME: {
                goToGameCard();
                break;
            }
            case MENU: {
                goToMenuCard();
                break;
            }
            case HELP: {
                goToHelpCard();
                break;
            }
            case LOAD: {
                goToLoadGame();
                break;
            }
        }
    }

    private void goToGameCard() {
        cardLayout.show(cards, Scene.GAME.toString());
        this.activeView = this.gameView;
        this.gameView.requestFocusInWindow();
    }

    private void goToMenuCard() {
        cardLayout.show(cards, Scene.MENU.toString());
        this.activeView = this.menuView;
        this.menuView.requestFocusInWindow();
    }

    private void goToHelpCard() {
        cardLayout.show(cards, Scene.HELP.toString());
        this.activeView = this.helpView;
        this.helpView.requestFocusInWindow();
    }

    private void goToLoadGame() {
        cardLayout.show(cards, Scene.LOAD.toString());
        this.activeView = this.loadView;
        this.loadView.selectionReset();
        this.loadView.requestFocusInWindow();
    }
}
