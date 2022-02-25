package facade;

import DBdao.CustomerDBDAO;
import DBdao.CouponDBDAO;
import beans.Category;
import beans.Customer;
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
        this.customerDAO = new CustomerDBDAO();
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

        Customer thisCustomer = this.customerDAO.getOneCustomer(costumerID);

        return this.couponDAO.getCostumerCoupons(thisCustomer);
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


    public Customer getCostumerDetails(){

        return this.customerDAO.getOneCustomer(costumerID);
    }


    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        Customer customerFromFB = this.customerDAO.getOneCustomer(costumerID);

        if (customerFromFB == null) {
            throw new CouponSystemException(ErrMsg.LOGIN.getMsg());
        }

        return customerFromFB.getEmail().equals(email) &&
                customerFromFB.getPassword().equals(password);
    }
}
