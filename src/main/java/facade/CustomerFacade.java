package facade;

import DBdao.CouponDBDAO;
import DBdao.CustomerDBDAO;
import beans.Category;
import beans.Customer;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.LogInException;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

public class CustomerFacade extends ClientFacade {
    private int customerID;


    @Override
    public boolean login(String email, String password) {
        this.customerDAO = new CustomerDBDAO();
        int customerId = this.customerDAO.getCustomerId(email, password);

        if (customerId == 0) {
            this.customerDAO = null;
            return false;
        }
        this.couponDAO = new CouponDBDAO();
        this.customerID = customerId;
        return true;
    }


    public void purchaseCoupon(Coupon coupon) throws CouponSystemException, LogInException {

        checkLoggedIn();

        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException("Can not make purchase - coupon is out of date");
        }

        if (this.couponDAO.isCostumerCouponExists(customerID, coupon.getId())) {
            throw new CouponSystemException("Can not make purchase - you already own this coupon");
        }

        Coupon couponToPurchase = this.couponDAO.getOneCoupon(coupon.getId());

        if (couponToPurchase.getAmount() == 0) {
            throw new CouponSystemException("Can not make purchase - coupon is out of stock");
        }

        getCustomerDetails().getCoupons().add(couponToPurchase);

        this.couponDAO.addCouponsPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon purchased");

        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        this.couponDAO.updateCoupon(couponToPurchase);
    }


    public List<Coupon> getCustomerCoupons() throws LogInException {
        checkLoggedIn();
        return this.couponDAO.getCostumerCoupons(customerID);
    }


    public List<Coupon> getCustomerCoupon(Category category) throws LogInException {
        checkLoggedIn();
        return this.couponDAO.getCustomerCouponsByCategory(category, customerID);
    }


    public List<Coupon> getCustomerCoupon(double maxPrice) throws  LogInException {
        checkLoggedIn();
        return this.couponDAO.getCustomerCouponsByMaxPrice(maxPrice, customerID);

    }


    public Customer getCustomerDetails() throws LogInException {
        checkLoggedIn();
        return this.customerDAO.getOneCustomer(customerID);
    }


    private void checkLoggedIn() throws  LogInException {
        if (this.customerDAO == null) {
            throw new LogInException("You are not logged in");
        }
    }
}
