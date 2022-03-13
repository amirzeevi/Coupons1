package exceptions;

/**
 * The class is a customized subclass of the {@link Exception} class that will throw an exception
 * when conditions in the Coupon System are not met. Each instance will carry a specific message according
 * to the method and operation that is throwing.
 */
public class CouponSystemException extends Exception {
    public CouponSystemException(String message) {
        super(message);
    }
}
