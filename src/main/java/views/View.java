package views;

import javax.swing.*;
import java.util.logging.Logger;

public abstract class View<C> extends JPanel {
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    protected C controller;

    /**
     * Initializes the View component in the Model-Control-View (MCV) pattern.
     * <p>
     * This abstract method is designed to be implemented by subclasses to set up
     * and configure the view elements. The specific details of the initialization
     * will depend on the concrete implementation of the view.
     * </p>
     */
    public abstract void init();

    /**
     * Updates the View component in the Model-Control-View (MCV) pattern.
     * <p>
     * This abstract method is designed to be implemented by subclasses to refresh
     * and update the view elements based on changes in the model. The specific details of the update process
     * will depend on the concrete
     * implementation of the view and the nature of the model changes.
     * </p>
     */
    public abstract void update();
}

