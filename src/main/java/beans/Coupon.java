package beans;

import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.Date;

public class Coupon {
    private int id;
    private int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    public Coupon(int id, int companyID, int CategoryValue, String title, String description, Date startDate, Date endDate, int amount, double price, String image) throws CouponSystemException {
        this.id = id;
        this.companyID = companyID;
        setCategory(CategoryValue);
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws CouponSystemException {
        throw new CouponSystemException(ErrMsg.CUSTOMER_ID_CHANGE.getMsg());
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) throws CouponSystemException {
        throw new CouponSystemException(ErrMsg.COUPON_COMPANY_ID_CHANGE.getMsg());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(int CategoryValue) throws CouponSystemException {
        if (CategoryValue - 1 >= Category.values().length || CategoryValue < 1) {
            throw new CouponSystemException(ErrMsg.CATEGORY.getMsg());
        }
        this.category = Category.values()[CategoryValue - 1];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", CompanyID=" + companyID +
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
