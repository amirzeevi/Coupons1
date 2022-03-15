package facade;

import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import beans.Category;
import beans.Customer;
import beans.Coupon;
import exceptions.CouponsSystemException;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

/**
 * This class is a facade as in the facade design pattern. it is to be used by the customer client.
 * It encapsulates all required internal connections to perform operations
 * for {@link Customer} and {@link Coupon} to the database.
 * In any case an operation can not be successfully made by the customer,
 * an {@link CouponsSystemException} exception will be thrown.
 */
public class CustomerFacade extends ClientFacade {
    private int customerID;

    /**
     * This method should be called first when the company client wishes to use the class.
     * if the email and password put in are matched with the database, it sets the company id and initiates
     * the {@link dao.CustomersDAO} and {@link dao.CouponsDAO}.
     */
    @Override
    public boolean login(String email, String password) {
        this.customersDAO = new CustomersDBDAO();
        this.customerID = this.customersDAO.getCustomerId(email, password);
        if (this.customerID == 0) {
            this.customersDAO = null;
            return false;
        }
        this.couponsDAO = new CouponsDBDAO();
        return true;
    }

    /**
     * This method will add the specified coupon purchase to tha customers_coupons table containing the coupon id
     * and the customer id. If any condition does not meet with requirements for purchase
     * it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void purchaseCoupon(Coupon coupon) throws CouponsSystemException {

        Coupon couponFromDB = this.couponsDAO.getOneCoupon(coupon.getId());

        if (couponFromDB == null) {
            throw new CouponsSystemException("Coupon not exist");
        }
        if (couponFromDB.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponsSystemException("Can not make purchase - coupon is out of date");
        }
        if (couponFromDB.getAmount() == 0) {
            throw new CouponsSystemException("Can not make purchase - coupon is out of stock");
        }
        if (this.couponsDAO.isCostumerCouponExist(customerID, couponFromDB.getId())) {
            throw new CouponsSystemException("Can not make purchase - you already own this coupon");
        }
        this.couponsDAO.addCouponPurchase(this.customerID, coupon.getId());
        System.out.println("Coupon purchased");
    }

    /**
     * This method returns a list of all the coupons the customer owns.
     */
    public List<Coupon> getCustomerCoupons(){
        return this.couponsDAO.getCostumerCoupons(customerID);
    }

    /**
     * This method returns a list of all the coupons that are from the specified category that the customer owns.
     */
    public List<Coupon> getCustomerCoupons(Category category) {
        return this.couponsDAO.getCustomerCouponsByCategory(category, customerID);
    }

    /**
     * This method returns a list of all the coupons that are of the specified maximum price that the customer owns.
     */
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return this.couponsDAO.getCustomerCouponsByMaxPrice(maxPrice, customerID);
    }

    /**
     * This method returns the customer from the database.
     */
    public Customer getCustomerDetails() {
        return this.customersDAO.getOneCustomer(customerID);
    }
}
