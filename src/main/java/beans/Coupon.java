package beans;

import exceptions.ValueSetNotAllowed;


import java.util.Date;

/**
 * Coupon bean to specify attributes and set and toString operations
 */
public class Coupon {
    private int id;
    private final int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    /**
     * Constructs the coupon. id is included for when retrieving from database.
     */
    public Coupon(int id, int companyID, Category category, String title,
                  String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.id = id;
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    /**
     * Required constructor for when initializing a new coupon.
     */
    public Coupon(int companyID, Category category, String title,
                  String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    /**
     * Returns coupon id
     */
    public int getId() {
        return id;
    }

    /**
     * Can not change coupon id will throw exception
     *
     * @throws ValueSetNotAllowed
     */
    public void setId(int id) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change coupon id");
    }

    /**
     * Returns the company that owns the coupon
     */
    public int getCompanyID() {
        return companyID;
    }

    /**
     * Can not change the company id. will throw exception
     *
     * @throws ValueSetNotAllowed
     */
    public void setCompanyID(int companyID) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change coupon company id");
    }

    /**
     * Returns the coupon category enum
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the coupon category enum
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Returns the coupon title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the coupon title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the coupon description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the coupon description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns coupon start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets coupon start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns coupon end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets coupon end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns coupon amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets coupon amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns coupon price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets coupon price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns coupon image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets coupon image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Overrides toString method to display coupon details
     */
    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
