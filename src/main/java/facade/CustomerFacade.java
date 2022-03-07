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

public class CustomerFacade extends ClientFacade {
    private final int customerID;

    public CustomerFacade(int customerID) {
        this.customerID = customerID;
        this.customerDAO = new CustomerDBDAO();
        this.couponDAO = new CouponDBDAO();
    }


    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

        // because can't delete expired coupons at exactly midnight using cron ?, should check if expired
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED.getMsg());
        }

        if (this.couponDAO.isCostumerCouponExists(customerID, coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_EXISTS.getMsg());
        }

        Coupon couponToPurchase = this.couponDAO.getOneCoupon(coupon.getId());

        if (couponToPurchase.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT.getMsg());
        }

        this.couponDAO.addCouponsPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon " + coupon.getTitle() + " purchased");
        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        this.couponDAO.updateCoupon(couponToPurchase);
    }


    public ArrayList<Coupon> getCostumerCoupons() throws CouponSystemException {

        Customer thisCustomer = this.customerDAO.getOneCustomer(customerID);

        ArrayList<Coupon> costumerCoupon = this.couponDAO.getCostumerCoupons(thisCustomer);

        if (costumerCoupon.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return costumerCoupon;
    }


    public ArrayList<Coupon> getCustomerCoupon(Category category) throws CouponSystemException {

        List<Coupon> categoryCoupons = getCostumerCoupons().stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toList());

        if (categoryCoupons.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return (ArrayList<Coupon>) categoryCoupons;
    }


    public ArrayList<Coupon> getCustomerCoupon(double maxPrice) throws CouponSystemException {

        List<Coupon> maxPriceCoupons = getCostumerCoupons().stream()
                .filter(coupon -> coupon.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        if (maxPriceCoupons.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return (ArrayList<Coupon>) maxPriceCoupons;
    }


    public Customer getCustomerDetails() {

        return this.customerDAO.getOneCustomer(customerID);
    }


    @Override
    public boolean login(String email, String password) throws CouponSystemException {

        Customer customerFromFB = this.customerDAO.getOneCustomer(customerID);

        if (customerFromFB == null) {
            throw new CouponSystemException(ErrMsg.LOGIN.getMsg());
        }

        return customerFromFB.getEmail().equals(email) &&
                customerFromFB.getPassword().equals(password);
    }
}
