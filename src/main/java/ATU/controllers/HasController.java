package ATU.controllers;

/**
 * Denotes a component having a controller
 *
 * @param <C> The type of the controller
 */
public interface HasController<C> {

    /**
     * @return The controller of the component.
     */
    C getController();
}
