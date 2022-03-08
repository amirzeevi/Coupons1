package dao;

import beans.Category;
import beans.Coupon;

import java.util.List;

public interface CouponsDAO {

    boolean isCouponCompanyExists(Coupon coupon);

    boolean isCostumerCouponExists(int costumerID, int couponID);

    void addCoupons(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(int couponID);

    void addCouponsPurchase(int costumerID, int couponID);

    void deleteExpiredCoupons();

    Coupon getOneCoupon(int couponID);

    List<Coupon> getAllCoupons();

    List<Coupon> getCompanyCouponsByCategory(Category category, int companyId);

    List<Coupon> getCompanyCouponByMaxPrice(double MaxPrice, int companyId);

    List<Coupon> getCustomerCouponsByCategory(Category category, int customerId);

    List<Coupon> getCustomerCouponByMaxPrice(double maxPrice, int customerId);

    List<Coupon> getCompanyCoupons(int companyID);

    List<Coupon> getCostumerCoupons(int customerId);


}

