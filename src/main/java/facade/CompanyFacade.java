package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CouponDBDAO;
import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.LogInException;

import java.util.List;

public class CompanyFacade extends ClientFacade {
    private int companyID;


    @Override
    public boolean login(String email, String password) {
        this.companiesDAO = new CompaniesDBDAO();
        int companyId = this.companiesDAO.getCompanyId(email, password);

        if (companyId == 0) {
            this.companiesDAO = null;
            return false;
        }
        this.companyID = companyId;
        this.couponDAO = new CouponDBDAO();
        return true;
    }

    public void addCoupon(Coupon coupon) throws CouponSystemException, LogInException {
        checkLoggedIn();
        checkCouponInfo(coupon);

        if (this.couponDAO.isCouponCompanyExists(coupon)) {
            throw new CouponSystemException("Can not add - coupon already exists");
        }

        getCompanyDetails().getCoupons().add(coupon);
        this.couponDAO.addCoupons(coupon);

        System.out.println("Coupon added");
    }


    public void updateCoupon(Coupon coupon) throws CouponSystemException, LogInException {

        checkLoggedIn();
        checkCouponInfo(coupon);

        Coupon couponFromDB = getOneCoupon(coupon.getId());

        if (!(couponFromDB.getTitle().equals(coupon.getTitle()))) {
            if (this.couponDAO.isCouponCompanyExists(coupon)) {
                throw new CouponSystemException("Can not update coupon to existing title");
            }
        }

        getCompanyDetails().getCoupons().stream().
                filter(listCoupon -> listCoupon.getId() == coupon.getId())
                .forEach(listCoupon -> listCoupon = coupon);

        this.couponDAO.updateCoupon(coupon);
        System.out.println("Coupon updated");
    }


    public void deleteCoupon(int couponID) throws CouponSystemException, LogInException {

        checkLoggedIn();
        Coupon couponFromDB = getOneCoupon(couponID);

        if (couponFromDB.getCompanyID() != companyID) {
            throw new CouponSystemException("Coupon does not belong to this company");
        }

        getCompanyDetails().getCoupons().removeIf(coupon -> coupon.getId() == couponID);

        this.couponDAO.deleteCoupon(couponID);
        System.out.println("Coupon deleted");
    }


    public List<Coupon> getCompanyCoupons() throws LogInException {
        checkLoggedIn();
        return this.couponDAO.getCompanyCoupons(companyID);
    }


    public List<Coupon> getCompanyCoupons(Category category) throws LogInException {
        checkLoggedIn();
        return this.couponDAO.getCompanyCouponsByCategory(category, companyID);
    }


    public List<Coupon> getCompanyCoupons(double maxPrice) throws  LogInException {
        checkLoggedIn();
        return this.couponDAO.getCompanyCouponByMaxPrice(maxPrice, companyID);
    }


    public Company getCompanyDetails() throws LogInException {
        checkLoggedIn();
        return this.companiesDAO.getOneCompany(companyID);
    }


    public Coupon getOneCoupon(int couponID) throws CouponSystemException, LogInException {
        checkLoggedIn();

        Coupon couponFromDB = this.couponDAO.getOneCoupon(couponID);

        if (couponFromDB == null) {
            throw new CouponSystemException("Coupon not found");
        }
        return couponFromDB;
    }


    private void checkCouponInfo(Coupon coupon) throws CouponSystemException {

        if (coupon.getCompanyID() != companyID) {
            throw new CouponSystemException("Company id  incorrect");
        }
        if (coupon.getStartDate().after(coupon.getEndDate())) {
            throw new CouponSystemException("Coupon date incorrect");
        }
        if (coupon.getPrice() < 0) {
            throw new CouponSystemException("Coupon price should be positive");
        }
        if (coupon.getAmount() < 1) {
            throw new CouponSystemException("Coupon amount should be positive");
        }
    }

    private void checkLoggedIn() throws  LogInException {
        if (this.couponDAO == null) {
            throw new LogInException("You are not logged in");
        }
    }
}
