
import DBdao.CouponDBDAO;
import beans.Coupon;
import dao.CouponsDAO;
import facade.AdminFacade;
import jobs.CouponExpirationDailyJob;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Program {

    public static void main(String[] args) {
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

//
//        CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
//        Thread t = new Thread(couponExpirationDailyJob);
//        t.start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException ignored) {
//        }
//        couponExpirationDailyJob.stop();
        CouponsDAO couponsDAO = new CouponDBDAO();
        couponsDAO.getAllCoupons().stream()
                .filter(coupon -> coupon.getStartDate().after(coupon.getEndDate()))
                .forEach(coupon -> couponsDAO.deleteCoupon(coupon.getId()));
    }
}



