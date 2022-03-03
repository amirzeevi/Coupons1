import jobs.CouponExpirationDailyJob;


public class Test {
    public static void testAll() {
        CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
        Thread t = new Thread(couponExpirationDailyJob);
        t.start();
        couponExpirationDailyJob.stop();
    }
}
