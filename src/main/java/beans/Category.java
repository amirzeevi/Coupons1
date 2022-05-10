package beans;

public enum Category {
    FOOD,
    ELECTRICITY,
    RESTAURANT,
    VACATION,
    HOLIDAY,
    MUSEUM;

    public final int value = ordinal() + 1;
}
