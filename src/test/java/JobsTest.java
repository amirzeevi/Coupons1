import beans.Category;
import beans.Coupon;
import dbdao.CouponDBDAO;
import jobs.CouponExpirationDailyJob;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class JobsTest {

    @Before
    public void setUp() {
        Coupon expired = new Coupon(
                0,
                2,
                Category.FOOD,
                "Expired",
                "Description",
                Date.valueOf(LocalDate.now().minusDays(14)),
                Date.valueOf(LocalDate.now().minusDays(1)),
                1,
                1.99,
                "book"
        );
        CouponDBDAO couponDBDAO = new CouponDBDAO();
        couponDBDAO.addCoupon(expired);
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
