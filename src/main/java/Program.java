
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
//        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
//        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
//        DBrunQuery.runQuery(DBmanager.CREATE_COSTUMERS_TABLE);
//        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
//        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
//        DBrunQuery.runQuery(DBmanager.CREATE_COSTUMERS_COUPONS_TABLE);
//
//        for (Category category : Category.values()) {
//            Map<Integer, Object> values = Map.of(1, category);
//            DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, values);
//        }

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



