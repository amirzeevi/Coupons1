
import DBdao.CouponDBDAO;
import beans.Category;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import dao.CouponsDAO;
import exceptions.CouponSystemException;
import exceptions.LogInException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.LoginManager;
import jobs.CouponExpirationDailyJob;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Program {

    public static void main(String[] args) throws CouponSystemException, LogInException {


//        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
        CompanyFacade companyFacade = (CompanyFacade)LoginManager.getInstance().login("company@com", "password", ClientType.COMPANY);
//        Company company = adminFacade.getOneCompany(6);
//        Coupon coupon = company.getCoupons().get(0);
//        System.out.println(coupon);
//        coupon.setPrice(200.50);
//        companyFacade.updateCoupon(coupon);
//        company.getCoupons().forEach(System.out::println);
        companyFacade.getCompanyCoupons(Category.ELECTRICITY).forEach(System.out::println);
    }
}



