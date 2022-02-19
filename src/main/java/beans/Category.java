package beans;

/**
 * An enum to describe different types of coupons to be used
 */
public enum Category {
    FOOD, ELECTRICITY, RESTAURANT, VACATION;
    public final int value = ordinal() + 1;


}
