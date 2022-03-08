package facade;

import DBdao.CouponDBDAO;
import DBdao.CustomerDBDAO;
import beans.Category;
import beans.Customer;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

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


    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        checkLogin();

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

        getCustomerDetails().getCoupons().add(couponToPurchase);

        this.couponDAO.addCouponsPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon " + coupon.getTitle() + " purchased");

        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
        this.couponDAO.updateCoupon(couponToPurchase);
    }


    public List<Coupon> getCustomerCoupons() throws CouponSystemException {
        checkLogin();
        return this.couponDAO.getCostumerCoupons(customerID);
    }


    public List<Coupon> getCustomerCoupon(Category category) throws CouponSystemException {
        checkLogin();
        return getCustomerCoupons().stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toList());
    }


    public List<Coupon> getCustomerCoupon(double maxPrice) throws CouponSystemException {
        checkLogin();
        return getCustomerCoupons().stream()
                .filter(coupon -> coupon.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }


    public Customer getCustomerDetails() throws CouponSystemException {
        checkLogin();
        return this.customerDAO.getOneCustomer(customerID);
    }


    private void checkLogin() throws CouponSystemException {
        if (this.customerDAO == null) {
            throw new CouponSystemException("You are not logged in properly");
        }
    }
}
