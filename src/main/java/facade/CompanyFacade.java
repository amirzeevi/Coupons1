package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CouponDBDAO;
import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompanyFacade extends ClientFacade {
    private final int companyID;

    public CompanyFacade(int companyID) {
        this.companyID = companyID;
        this.companiesDAO = new CompaniesDBDAO();
        this.couponDAO = new CouponDBDAO();
    }


    public void addCoupon(Coupon coupon) throws CouponSystemException {

        checkCompanyID(coupon);
        checkCouponDate(coupon);

        if (this.couponDAO.isCouponCompanyExists(coupon)) {
            throw new CouponSystemException(ErrMsg.COUPON_ADD.getMsg() + ErrMsg.COUPON_TITLE_EXISTS.getMsg());
        }

        this.couponDAO.addCoupons(coupon);
        System.out.println("Coupon " + coupon.getTitle() + " added");
    }


    public void updateCoupon(Coupon coupon) throws CouponSystemException {

        checkCompanyID(coupon);
        checkCouponDate(coupon);

        Coupon couponFromDB = getOneCoupon(coupon.getId());

        if (!(couponFromDB.getTitle().equals(coupon.getTitle()))) {
            if (this.couponDAO.isCouponCompanyExists(coupon)) {
                throw new CouponSystemException(ErrMsg.COUPON_TITLE_EXISTS.getMsg());
            }
        }
        this.couponDAO.updateCoupon(coupon);
        System.out.println("Coupon " + coupon.getTitle() + " updated");
    }


    public void deleteCoupon(int couponID) throws CouponSystemException {

        Coupon couponFromDB = getOneCoupon(couponID);
        checkCompanyID(couponFromDB);

        this.couponDAO.deleteCoupon(couponID);
        System.out.println("Coupon " + couponFromDB.getTitle() + " deleted");
    }


    public ArrayList<Coupon> getCompanyCoupons() throws CouponSystemException {

        List<Coupon> companyCoupons = this.couponDAO.getCompanyCoupons(companyID);

        if (companyCoupons.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return (ArrayList<Coupon>) companyCoupons;
    }


    public ArrayList<Coupon> getCompanyCoupons(Category category) {

        Predicate<Coupon> categoryCoupons = coupon -> coupon.getCategory().equals(category);

        List<Coupon> companyCoupons = this.couponDAO.getCompanyCoupons(companyID).stream()
                .filter(categoryCoupons)
                .collect(Collectors.toList());

        return (ArrayList<Coupon>) companyCoupons;
    }


    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {

        Predicate<Coupon> maxPriceCoupons = coupon -> coupon.getPrice() <= maxPrice;

        List<Coupon> coupons = this.couponDAO.getCompanyCoupons(companyID).stream()
                .filter(maxPriceCoupons)
                .collect(Collectors.toList());

        return (ArrayList<Coupon>) coupons;
    }


    public Company getCompanyDetails() {

        return this.companiesDAO.getOneCompany(companyID);
    }


    public Coupon getOneCoupon(int couponID) throws CouponSystemException {

        Coupon couponFromDB = this.couponDAO.getOneCoupon(couponID);

        if (couponFromDB == null) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS.getMsg());
        }

        return couponFromDB;
    }


    private void checkCompanyID(Coupon coupon) throws CouponSystemException {

        if (coupon.getCompanyID() != companyID) {
            throw new CouponSystemException(ErrMsg.COUPON_COMPANY_ID_NOT_SAME.getMsg());
        }
    }


    private void checkCouponDate(Coupon coupon) throws CouponSystemException {

        if (coupon.getEndDate().before(coupon.getStartDate())) {
            throw new CouponSystemException(ErrMsg.COUPON_DATE.getMsg());
        }
    }


    @Override
    public boolean login(String email, String password) throws CouponSystemException {

        Company companyFromDB = this.companiesDAO.getOneCompany(companyID);

        if (companyFromDB == null) {
            throw new CouponSystemException(ErrMsg.LOGIN.getMsg());
        }

        return companyFromDB.getEmail().equals(email) &&
                companyFromDB.getPassword().equals(password);
    }
}
