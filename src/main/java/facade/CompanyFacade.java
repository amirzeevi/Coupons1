package facade;

import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponsSystemException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * This class is a facade as in the facade design pattern. it is to be used by the company client.
 * It encapsulates all required internal connections to perform operations
 * for {@link Company} and {@link Coupon} to the database.
 * In any case an operation can not be successfully made by the company,
 * an {@link CouponsSystemException} exception will be thrown.
 */
public class CompanyFacade extends ClientFacade {
    private int companyID;

    /**
     * This method should be called first when the company client wishes to use the class.
     * if the email and password put in are matched with the database, it sets the company id and initiates
     * the {@link dao.CompaniesDAO} and {@link dao.CouponsDAO}.
     */
    @Override
    public boolean login(String email, String password) {
        this.companiesDAO = new CompaniesDBDAO();
        this.companyID = this.companiesDAO.getCompanyId(email, password);
        if (this.companyID == 0) {
            this.companiesDAO = null;
            return false;
        }
        this.couponsDAO = new CouponsDBDAO();
        return true;
    }

    /**
     * This method will add the specified Coupon to the database. If any condition does not meet with requirements
     * it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void addCoupon(Coupon coupon) throws CouponsSystemException {
        checkCouponData(coupon);
        if (this.couponsDAO.isCompanyCouponExist(coupon)) {
            throw new CouponsSystemException("Can not add - coupon already exists");
        }
        this.couponsDAO.addCoupon(coupon);
        System.out.println("Coupon added");
    }

    /**
     * This method will update the specified Coupon in the database. If any condition does not meet with requirements
     * it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void updateCoupon(Coupon coupon) throws CouponsSystemException {
        checkCouponData(coupon);
        if (this.couponsDAO.getOneCoupon(coupon.getId()) == null) {
            throw new CouponsSystemException("Coupon not found");
        }
        if (this.couponsDAO.updateCouponIsTitleExist(coupon)) {
            throw new CouponsSystemException("Can not update coupon existing title");
        }
        this.couponsDAO.updateCoupon(coupon);
        System.out.println("Coupon updated");
    }

    /**
     * This method will delete the specified Coupon in the database.
     * If it does not find the coupon in the database or the coupon does not belong to this company,
     * will throw an {@link CouponsSystemException} exception with a specific message.
     */
    public void deleteCoupon(int couponID) throws CouponsSystemException {
        Coupon couponFromDB = this.couponsDAO.getOneCoupon(couponID);
        if (couponFromDB == null) {
            throw new CouponsSystemException("Coupon not found");
        }
        if (couponFromDB.getCompanyID() != companyID) {
            throw new CouponsSystemException("Coupon does not belong to this company");
        }
        this.couponsDAO.deleteCoupon(couponID);
        System.out.println("Coupon deleted");
    }

    /**
     * This method returns all the company coupons from the database as a list.
     */
    public List<Coupon> getCompanyCoupons() throws CouponsSystemException {
        List<Coupon> coupons = this.couponsDAO.getCompanyCoupons(companyID);
        if(coupons.isEmpty()){
            throw new CouponsSystemException("No coupons found");
        }
        return coupons;
    }

    /**
     * This method returns all the company coupons that are of the specified category as a list.
     */
    public List<Coupon> getCompanyCoupons(Category category) throws CouponsSystemException {
        List<Coupon> categoryCoupons = this.couponsDAO.getCompanyCouponsByCategory(category, companyID);
        if(categoryCoupons.isEmpty()){
            throw new CouponsSystemException("No coupon found");
        }
        return categoryCoupons;
    }

    /**
     * This method returns all the company coupons that are of the specified maximum price as a list.
     */
    public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponsSystemException {
        List<Coupon> maxPriceCoupons = this.couponsDAO.getCompanyCouponsByMaxPrice(maxPrice, companyID);
        if (maxPriceCoupons.isEmpty()) {
            throw new CouponsSystemException("No coupons found");
        }
        return maxPriceCoupons;
    }

    /**
     * This method returns the company from database based on its id.
     */
    public Company getCompanyDetails() {
        return this.companiesDAO.getOneCompany(companyID);
    }

    /**
     * This private method is meant to perform checks on the specified coupon data, to be used in different
     * methods in this facade.
     */
    private void checkCouponData(Coupon coupon) throws CouponsSystemException {
        if (coupon == null) {
            throw new CouponsSystemException("Invalid coupon");
        }
        if (coupon.getCompanyID() != companyID) {
            throw new CouponsSystemException("Coupon company id incorrect");
        }
        if (coupon.getStartDate().after(coupon.getEndDate()) ||
                coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponsSystemException("Coupon date incorrect");
        }
        if (coupon.getPrice() < 1) {
            throw new CouponsSystemException("Coupon price should be positive");
        }
        if (coupon.getAmount() < 1) {
            throw new CouponsSystemException("Coupon amount should be positive");
        }
    }
}
