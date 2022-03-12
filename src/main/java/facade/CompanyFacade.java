package facade;

import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class CompanyFacade extends ClientFacade {
    private int companyID;

    @Override
    public boolean login(String email, String password) {
        this.companyID = new CompaniesDBDAO().getCompanyId(email, password);
        if (this.companyID == 0) {
            return false;
        }
        this.couponsDAO = new CouponsDBDAO();
        this.companiesDAO = new CompaniesDBDAO();
        return true;
    }

    public void addCoupon(Coupon coupon) throws CouponSystemException {
        checkCouponData(coupon);
        if (this.couponsDAO.isCompanyCouponExist(coupon)) {
            throw new CouponSystemException("Can not add - coupon already exists");
        }
        getCompanyDetails().getCoupons().add(coupon);

        this.couponsDAO.addCoupon(coupon);
        System.out.println("Coupon added");
    }

    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        checkCouponData(coupon);
        if (this.couponsDAO.updateCouponTitleExist(coupon)) {
            throw new CouponSystemException("Can not update coupon existing title");
        }
        getCompanyDetails().getCoupons().stream().
                filter(listCoupon -> listCoupon.getId() == coupon.getId()).
                forEach(listCoupon -> listCoupon = coupon);

        this.couponsDAO.updateCoupon(coupon);
        System.out.println("Coupon updated");
    }

    public void deleteCoupon(int couponID) throws CouponSystemException {
        Coupon couponFromDB = getOneCoupon(couponID);
        if (couponFromDB.getCompanyID() != companyID) {
            throw new CouponSystemException("Coupon does not belong to this company");
        }
        getCompanyDetails().getCoupons().removeIf(coupon -> coupon.getId() == couponID);

        this.couponsDAO.deleteCoupon(couponID);
        System.out.println("Coupon deleted");
    }

    public List<Coupon> getCompanyCoupons() {
        return this.couponsDAO.getCompanyCoupons(companyID);
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        return this.couponsDAO.getCompanyCouponsByCategory(category, companyID);
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return this.couponsDAO.getCompanyCouponsByMaxPrice(maxPrice, companyID);
    }

    public Company getCompanyDetails() {
        return this.companiesDAO.getOneCompany(companyID);
    }

    public Coupon getOneCoupon(int couponID) throws CouponSystemException {
        Coupon couponFromDB = this.couponsDAO.getOneCoupon(couponID);
        if (couponFromDB == null) {
            throw new CouponSystemException("Coupon not found");
        }
        return couponFromDB;
    }

    private void checkCouponData(Coupon coupon) throws CouponSystemException {
        if (coupon == null) {
            throw new CouponSystemException("Coupon not found");
        }
        if (coupon.getCompanyID() != companyID) {
            throw new CouponSystemException("Coupon company id incorrect");
        }
        if (coupon.getStartDate().after(coupon.getEndDate()) ||
                coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException("Coupon date incorrect");
        }
        if (coupon.getPrice() < 1) {
            throw new CouponSystemException("Coupon price should be positive");
        }
        if (coupon.getAmount() < 1) {
            throw new CouponSystemException("Coupon amount should be positive");
        }
    }
}
