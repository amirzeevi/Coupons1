import beans.Category;
import beans.Coupon;
import dbdao.CouponsDBDAO;
import jobs.CouponExpirationDailyJob;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Test class for the {@link CouponExpirationDailyJob} class. Will test add an expired coupon to database,
 * and creating the thread that scans and deletes them.
 */
public class JobsTest {

    @BeforeEach
    public void setUp() {
        Coupon expiredCoupon = new Coupon(
                0,
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
}
