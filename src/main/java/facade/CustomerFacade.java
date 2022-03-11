package facade;

import dbdao.CouponDBDAO;
import dbdao.CustomerDBDAO;
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
        int customerId = new CustomerDBDAO().getCustomerId(email, password);

        if (customerId == 0) {
            return false;
        }
        this.couponDAO = new CouponDBDAO();
        this.customerDAO = new CustomerDBDAO();
        this.customerID = customerId;
        return true;
    }

    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException("Can not make purchase - coupon is out of date");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException("Can not make purchase - coupon is out of stock");
        }
        if (this.couponDAO.isCostumerCouponExist(customerID, coupon.getId())) {
            throw new CouponSystemException("Can not make purchase - you already own this coupon");
        }

        getCustomerDetails().getCoupons().add(coupon);
        this.couponDAO.addCouponsPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon purchased");

        coupon.setAmount(coupon.getAmount() - 1);
        this.couponDAO.updateCoupon(coupon);
    }

    public List<Coupon> getCustomerCoupons() {
        return this.couponDAO.getCostumerCoupons(customerID);
    }

    public List<Coupon> getCustomerCoupon(Category category) {
        return this.couponDAO.getCustomerCouponsByCategory(category, customerID);
    }

    public List<Coupon> getCustomerCoupon(double maxPrice) {
        return this.couponDAO.getCustomerCouponsByMaxPrice(maxPrice, customerID);

    }

    public Customer getCustomerDetails() {
        return this.customerDAO.getOneCustomer(customerID);
    }
}
