package exceptions;

/**
 * /**
 *  * The class is a customized subclass of the {@link Exception} class that will throw an exception
 *  * when logging in the system does not process successfully. instances will carry a specific message according
 *  * to the operation that is throwing.
 *  */

public class LogInException extends Exception{
    public LogInException(String message) {
        super(message);
    }
}
