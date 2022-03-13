package facade;

import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import beans.Category;
import beans.Customer;
import beans.Coupon;
import exceptions.CouponSystemException;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

public class CustomerFacade extends ClientFacade {
    private int customerID;

    @Override
    public boolean login(String email, String password) {
        this.customerDAO = new CustomersDBDAO();
        this.customerID = this.customerDAO.getCustomerId(email, password);
        if (this.customerID == 0) {
            this.customerDAO = null;
            return false;
        }
        this.couponsDAO = new CouponsDBDAO();
        return true;
    }

    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        if (coupon == null) {
            throw new CouponSystemException("Coupon does not exist");
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException("Can not make purchase - coupon is out of date");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException("Can not make purchase - coupon is out of stock");
        }
        if (this.couponsDAO.isCostumerCouponExist(customerID, coupon.getId())) {
            throw new CouponSystemException("Can not make purchase - you already own this coupon");
        }
        getCustomerDetails().getCoupons().add(coupon);

        this.couponsDAO.addCouponsPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon purchased");
    }

    public List<Coupon> getCustomerCoupons() {
        return this.couponsDAO.getCostumerCoupons(customerID);
    }

    public List<Coupon> getCustomerCoupons(Category category) {
        return this.couponsDAO.getCustomerCouponsByCategory(category, customerID);
    }

    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return this.couponsDAO.getCustomerCouponsByMaxPrice(maxPrice, customerID);

    }

    public Customer getCustomerDetails() {
        return this.customerDAO.getOneCustomer(customerID);
    }
}
