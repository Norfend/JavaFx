package views;

import javax.swing.*;

public abstract class View<C> extends JPanel {
    protected C controller;

    public abstract void init();

    public abstract void update();
}

