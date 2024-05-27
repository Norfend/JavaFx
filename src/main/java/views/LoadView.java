package views;

import controllers.LoadController;
import enums.Scene;
import launcher.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;

public class LoadView extends View<LoadController> implements KeyListener {
    private final JButton loadGameButton = new JButton("Load Game");
    private JList<String> jList;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    public LoadView(LoadController loadController) {
        this.controller = loadController;
    }

    public void init() {
        controller.setSaveFiles();
        listModel.addAll(controller.getSaveFiles());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jList);
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(scrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 150;
        this.add(loadGameButton, gbc);
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = jList.getSelectedValue();
                controller.jListSelection(selectedValue);
            }
        });
        loadGameButton.addActionListener(e -> controller.loadGameButton());
        setFocusable(true);
        addKeyListener(this);
        LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");

    }

    public void update() {
        controller.updateSaveFiles();
        DefaultListModel<String> update = new DefaultListModel<>();
        int temp = this.jList.getSelectedIndex();
        update.addAll(controller.getSaveFiles());
        this.jList.setModel(update);
        this.jList.setSelectedIndex(temp);
    }

    /**
     * Clears the selection in the {@code JList} component within this {@code JPanel}.
     * <p>
     * This method resets the selection state of the {@code JList} by clearing any
     * selected items, ensuring that no elements are selected.
     * </p>
     */
    public void selectionReset() {
        this.jList.clearSelection();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Handles key release events.
     * <p>
     * This method is triggered when a key is released. If the released key is the
     * Escape key, it will navigate to the MENU scene in the main application frame.
     * </p>
     *
     * @param e the {@code KeyEvent} that indicates a key was released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        LOGGER.log(Level.INFO, Thread.currentThread().getStackTrace()[1].getMethodName()
                + " function of " + Thread.currentThread().getStackTrace()[1].getClassName() + " has been called\n");
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            MainFrame.getInstance().goToCard(Scene.MENU);
        }
    }
}
