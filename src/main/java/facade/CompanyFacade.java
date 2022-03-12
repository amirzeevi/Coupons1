package facade;

import dao.CompaniesDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CouponDBDAO;
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
        this.couponDAO = new CouponDBDAO();
        this.companiesDAO = new CompaniesDBDAO();
        return true;
    }

    public void addCoupon(Coupon coupon) throws CouponSystemException {
        checkCouponInfo(coupon);
        if (this.couponDAO.isCompanyCouponExist(coupon)) {
            throw new CouponSystemException("Can not add - coupon already exists");
        }
        getCompanyDetails().getCoupons().add(coupon);

        this.couponDAO.addCoupon(coupon);
        System.out.println("Coupon added");
    }

    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        checkCouponInfo(coupon);
        if (this.couponDAO.updateCouponTitleExist(coupon)) {
            throw new CouponSystemException("Can not update coupon to existing title");
        }
        getCompanyDetails().getCoupons().stream().
                filter(listCoupon -> listCoupon.getId() == coupon.getId()).
                forEach(listCoupon -> listCoupon = coupon);

        this.couponDAO.updateCoupon(coupon);
        System.out.println("Coupon updated");
    }

    public void deleteCoupon(int couponID) throws CouponSystemException {
        Coupon couponFromDB = getOneCoupon(couponID);
        if (couponFromDB.getCompanyID() != companyID) {
            throw new CouponSystemException("Coupon does not belong to this company");
        }
        getCompanyDetails().getCoupons().removeIf(coupon -> coupon.getId() == couponID);

        this.couponDAO.deleteCoupon(couponID);
        System.out.println("Coupon deleted");
    }

    public List<Coupon> getCompanyCoupons() {
        return this.couponDAO.getCompanyCoupons(companyID);
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        return this.couponDAO.getCompanyCouponsByCategory(category, companyID);
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return this.couponDAO.getCompanyCouponsByMaxPrice(maxPrice, companyID);
    }

    public Company getCompanyDetails() {
        return this.companiesDAO.getOneCompany(companyID);
    }

    public Coupon getOneCoupon(int couponID) throws CouponSystemException {
        Coupon couponFromDB = this.couponDAO.getOneCoupon(couponID);
        if (couponFromDB == null) {
            throw new CouponSystemException("Coupon not found");
        }
        return couponFromDB;
    }

    private void checkCouponInfo(Coupon coupon) throws CouponSystemException {
        if (coupon.getCompanyID() != companyID) {
            throw new CouponSystemException("Coupon company id incorrect");
        }
        if (coupon.getStartDate().after(coupon.getEndDate()) ||
                coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException("Coupon date incorrect");
        }
        if (coupon.getPrice() < 0) {
            throw new CouponSystemException("Coupon price should be positive");
        }
        if (coupon.getAmount() < 1) {
            throw new CouponSystemException("Coupon amount should be positive");
        }
    }
}
