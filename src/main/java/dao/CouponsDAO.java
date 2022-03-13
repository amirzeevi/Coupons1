package dao;

import beans.Category;
import beans.Coupon;

import java.util.List;
/**
 * The CouponDAO interface is to be implemented by the class
 * that should access the database and update the coupons table.
 */
public interface CouponsDAO {
    /**
     * Will return true if the specified coupon exist based on its title and company id
     */
    boolean isCompanyCouponExist(Coupon coupon);
    /**
     * Will return true if the specified coupon exist based on its title and customer id
     */
    boolean isCostumerCouponExist(int costumerID, int couponID);
    /**
     * When updating a coupon's title we need to make sure the title does not already exist for the same company.
     * Will return true if it finds another coupon with the same title.
     */
    boolean updateCouponTitleExist(Coupon coupon);
    /**
     * Adds the specified coupon to the coupons table.
     */
    void addCoupon(Coupon coupon);
    /**
     * Updates the specified coupon in the coupons table.
     */
    void updateCoupon(Coupon coupon);
    /**
     * Deletes the specified coupon from the coupons table.
     */
    void deleteCoupon(int couponID);
    /**
     * Adds the purchase to the coupon vs customers table if the customer does not already own this coupon, and
     * if it is in stock and did not expire.
     * Will also decrease the coupon's amount by 1.
     */
    void addCouponsPurchase(int costumerID, int couponID);
    /**
     * Deletes all expired coupons from the coupons table.
     */
    void deleteExpiredCoupons();
    /**
     * Returns a coupon from the database based on its id.
     */
    Coupon getOneCoupon(int couponID);
    /**
     * Returns all the company's coupons based on its company id as a list.
     */
    List<Coupon> getCompanyCoupons(int companyID);
    /**
     * Returns all the customer's coupons based on the customer id as a list.
     */
    List<Coupon> getCostumerCoupons(int customerId);
    /**
     * Returns all the company's coupons that are from the specified category as a list.
     */
    List<Coupon> getCompanyCouponsByCategory(Category category, int companyId);
    /**
     * Returns all the company's coupons that has the specified maximum price as a list.
     */
    List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice, int companyId);
    /**
     * Returns all the customer's coupons that are from the specified category as a list.
     */
    List<Coupon> getCustomerCouponsByCategory(Category category, int customerId);
    /**
     * Returns all the customer's coupons that has the specified maximum price as a list.
     */
    List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice, int customerId);
}

