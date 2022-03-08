import jobs.CouponExpirationDailyJob;
import org.junit.Test;

public class JobsTest {


    @Test public void dailyCouponDelete(){
        CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
        Thread t = new Thread(couponExpirationDailyJob);
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            assert (true);
            couponExpirationDailyJob.stop();
            System.out.println(e.getMessage());
        }
        couponExpirationDailyJob.stop();
    }
}
