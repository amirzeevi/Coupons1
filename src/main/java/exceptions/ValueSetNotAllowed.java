package exceptions;

/**
 * The class is a customized subclass of the {@link Exception} class that will throw an exception
 * when trying to set a value that doesn't allow changes. Each instance will carry a specific message according
 * to the method that is throwing.
 */

public class ValueSetNotAllowed extends Exception {
    public ValueSetNotAllowed(String message) {
        super(message);
    }
}
