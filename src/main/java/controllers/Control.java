package controllers;

import java.util.logging.Logger;

/**
 * Abstract class representing a controller in the Model-View-Controller (MVC) architectural pattern.
 * <p>
 * This class serves as a base class for controllers in the MVC pattern. It is parameterized by the type of model it manages.
 * </p>
 *
 * @param <M> the type of model associated with the controller
 */
public abstract class Control<M> {
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    M model;
}
