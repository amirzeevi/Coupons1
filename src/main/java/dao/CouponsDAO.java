package dao;

import beans.Coupon;

import java.util.List;

public interface CouponsDAO {

    boolean isCouponCompanyExists(Coupon coupon);

    void addCoupons(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(int couponID);

    void addCouponsPurchase(int costumerID, int couponID);

    boolean isCostumerCouponExists(int costumerID, int couponID);

    Coupon getOneCoupon(int couponID);

    List<Coupon> getAllCoupons();

    List<Coupon> getCompanyCoupons(int companyID);

    List<Coupon> getCostumerCoupons(int customerId);


}

