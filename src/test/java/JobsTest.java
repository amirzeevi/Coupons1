import beans.Category;
import beans.Company;
import beans.Coupon;
import db.DBmanager;
import db.DBrunQuery;
import dbdao.CategoriesDBDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import jobs.CouponExpirationDailyJob;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Test class for the {@link CouponExpirationDailyJob} class. Will test add an expired coupon to database,
 * and creating the thread that scans and deletes them.
 */
public class JobsTest {
    @BeforeAll
    static void beforeAll() {
        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.SET_TIME_ZONE);
        DBrunQuery.runQuery(DBmanager.CREATE_TRIGGER_COUPON_PURCHASE);
        new CategoriesDBDAO().addAllCategories();
        new CompaniesDBDAO().addCompany(new Company(
                "Company",
                "company@com",
                "1234"));
        Coupon expiredCoupon = new Coupon(
                1,
                Category.FOOD,
                "Expired",
                "Description",
                Date.valueOf(LocalDate.now().minusDays(14)),
                Date.valueOf(LocalDate.now().minusDays(1)),
                1,
                1.99,
                "book"
        );
        new CouponsDBDAO().addCoupon(expiredCoupon);
    }

    @Test
    public void dailyCouponDelete() {
        CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
        Thread t = new Thread(couponExpirationDailyJob);
        t.start();
        try {
            Thread.sleep(5000);
            couponExpirationDailyJob.stop();
            t.interrupt();
        } catch (InterruptedException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @AfterAll
    static void afterAll() {
        DBrunQuery.runQuery(DBmanager.DROP_SCHEMA);
    }
}
