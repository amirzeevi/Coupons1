package dao;

import beans.Costumer;
import beans.Coupon;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CouponsDAO {

    boolean isCouponExists(Coupon coupon);

    boolean isCouponCompanyExists(Coupon coupon);

    void addCoupons(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(int couponID);

    void addCouponsPurchase(int costumerID, int couponID);

    void deleteCouponPurchase(int costumerID, int couponID);

    boolean isCostumerCouponExists(int costumerID, int couponID);

    Coupon getOneCoupon(int couponID);

    ArrayList<Coupon> getAllCoupons();

    List<Coupon> getCompanyCoupons(int companyID);

    ArrayList<Coupon> getCostumerCoupons(Costumer costumer);


}

