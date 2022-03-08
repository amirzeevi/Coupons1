package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CouponDBDAO;
import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public void addCoupon(Coupon coupon) throws CouponSystemException {

        checkLogin();
        checkCouponInfo(coupon);

        if (this.couponDAO.isCouponCompanyExists(coupon)) {
            throw new CouponSystemException(ErrMsg.COUPON_ADD.getMsg() + ErrMsg.COUPON_TITLE_EXISTS.getMsg());
        }

        getCompanyDetails().getCoupons().add(coupon);
        this.couponDAO.addCoupons(coupon);

        System.out.println("Coupon " + coupon.getTitle() + " added");
    }


    public void updateCoupon(Coupon coupon) throws CouponSystemException {

        checkLogin();
        checkCouponInfo(coupon);

        Coupon couponFromDB = getOneCoupon(coupon.getId());

        if (!(couponFromDB.getTitle().equals(coupon.getTitle()))) {
            if (this.couponDAO.isCouponCompanyExists(coupon)) {
                throw new CouponSystemException(ErrMsg.COUPON_TITLE_EXISTS.getMsg());
            }
        }

        getCompanyDetails().getCoupons().stream().
                filter(couponFromList -> couponFromList.getId() == coupon.getId())
                .forEach(couponFromList -> couponFromList = coupon);

        this.couponDAO.updateCoupon(coupon);
        System.out.println("Coupon " + coupon.getTitle() + " updated");
    }


    public void deleteCoupon(int couponID) throws CouponSystemException {

        checkLogin();

        Coupon couponFromDB = getOneCoupon(couponID);

        if (couponFromDB.getCompanyID() != companyID) {
            throw new CouponSystemException("This coupon does not belong to this company");
        }

        getCompanyDetails().getCoupons().removeIf(coupon -> coupon.getId() == couponID);

        this.couponDAO.deleteCoupon(couponID);
        System.out.println("Coupon " + couponFromDB.getTitle() + " deleted");
    }


    public List<Coupon> getCompanyCoupons() throws CouponSystemException {
        checkLogin();

        return this.couponDAO.getCompanyCoupons(companyID);
    }


    public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
        checkLogin();

        Predicate<Coupon> categoryCoupons = coupon -> coupon.getCategory().equals(category);

        return this.couponDAO.getCompanyCoupons(companyID).stream()
                .filter(categoryCoupons)
                .collect(Collectors.toList());
    }


    public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
        checkLogin();

        Predicate<Coupon> maxPriceCoupons = coupon -> coupon.getPrice() <= maxPrice;

        return this.couponDAO.getCompanyCoupons(companyID).stream()
                .filter(maxPriceCoupons)
                .collect(Collectors.toList());
    }


    public Company getCompanyDetails() throws CouponSystemException {
        checkLogin();

        return this.companiesDAO.getOneCompany(companyID);
    }


    public Coupon getOneCoupon(int couponID) throws CouponSystemException {
        checkLogin();

        Coupon couponFromDB = this.couponDAO.getOneCoupon(couponID);

        if (couponFromDB == null) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS.getMsg());
        }
        return couponFromDB;
    }


    private void checkCouponInfo(Coupon coupon) throws CouponSystemException {

        if (coupon.getCompanyID() != companyID) {
            throw new CouponSystemException("Company id is not correct");
        }
        if (coupon.getStartDate().after(coupon.getEndDate())) {
            throw new CouponSystemException("Coupon date incorrect");
        }
        if (coupon.getPrice() < 0) {
            throw new CouponSystemException("Coupon price should be positive");
        }
        if (coupon.getAmount() < 0) {
            throw new CouponSystemException("Coupon amount should be positive");
        }
    }

    private void checkLogin() throws CouponSystemException {
        if (this.couponDAO == null) {
            throw new CouponSystemException("You are not logged in properly");
        }
    }
}
