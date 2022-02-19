package facade;

import DBdao.CostumerDBDAO;
import DBdao.CouponDBDAO;
import beans.Category;
import beans.Costumer;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CostumerFacade extends ClientFacade {
    private final int costumerID;

    public CostumerFacade(int costumerID) {
        this.costumerID = costumerID;
        this.costumerDAO = new CostumerDBDAO();
        this.couponDAO = new CouponDBDAO();
    }


    public void purchaseCoupon(Coupon coupon) throws CouponSystemException{

        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED.getMsg());
        }

        if (this.couponDAO.isCostumerCouponExists(costumerID, coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_EXISTS.getMsg());
        }

        Coupon couponToPurchase = this.couponDAO.getOneCoupon(coupon.getId());

        if (couponToPurchase.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT.getMsg());
        }

        this.couponDAO.addCouponsPurchase(this.costumerID, coupon.getId());
        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        this.couponDAO.updateCoupon(couponToPurchase);
    }


    public ArrayList<Coupon> getCostumerCoupons(){

        Costumer thisCostumer = this.costumerDAO.getOneCostumer(costumerID);

        return this.couponDAO.getCostumerCoupons(thisCostumer);
    }


    public ArrayList<Coupon> getCostumerCoupon(Category category){

        List<Coupon> categoryCoupons = getCostumerCoupons().stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toList());

        return (ArrayList<Coupon>) categoryCoupons;
    }


    public ArrayList<Coupon> getCostumerCoupon(double maxPrice){

        List<Coupon> maxPriceCoupons = getCostumerCoupons().stream()
                .filter(coupon -> coupon.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        return (ArrayList<Coupon>) maxPriceCoupons;
    }


    public Costumer getCostumerDetails(){

        return this.costumerDAO.getOneCostumer(costumerID);
    }


    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        Costumer costumerFromFB = this.costumerDAO.getOneCostumer(costumerID);

        if (costumerFromFB == null) {
            throw new CouponSystemException(ErrMsg.LOGIN.getMsg());
        }

        return costumerFromFB.getEmail().equals(email) &&
                costumerFromFB.getPassword().equals(password);
    }
}
