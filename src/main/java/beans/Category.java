package beans;

/**
 * An enum to describe different types of coupons to be used
 */
public enum Category {
    FOOD, ELECTRICITY, RESTAURANT, VACATION, HOLIDAY, MUSEUM;

    public final int value = ordinal() + 1;

}
