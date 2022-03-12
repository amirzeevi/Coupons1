package facade;

import dbdao.CouponDBDAO;
import dbdao.CustomerDBDAO;
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
       this.customerID = new CustomerDBDAO().getCustomerId(email, password);

        if (this.customerID == 0) {
            return false;
        }
        this.couponDAO = new CouponDBDAO();
        this.customerDAO = new CustomerDBDAO();
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
        if (this.couponDAO.isCostumerCouponExist(customerID, coupon.getId())) {
            throw new CouponSystemException("Can not make purchase - you already own this coupon");
        }
        getCustomerDetails().getCoupons().add(coupon);

        this.couponDAO.addCouponsPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon purchased");
    }

    public List<Coupon> getCustomerCoupons() {
        return this.couponDAO.getCostumerCoupons(customerID);
    }

    public List<Coupon> getCustomerCoupons(Category category) {
        return this.couponDAO.getCustomerCouponsByCategory(category, customerID);
    }

    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return this.couponDAO.getCustomerCouponsByMaxPrice(maxPrice, customerID);

    }

    public Customer getCustomerDetails() {
        return this.customerDAO.getOneCustomer(customerID);
    }
}
