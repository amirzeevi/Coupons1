package jobs;

import dbdao.CouponDBDAO;
import dao.CouponsDAO;


public class CouponExpirationDailyJob implements Runnable {

    private final CouponsDAO couponDAO;
    private boolean quit;

    public CouponExpirationDailyJob() {
        this.couponDAO = new CouponDBDAO();
        this.quit = false;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public void stop() {
        setQuit(true);
    }

    @Override
    public void run() {
        try {
            while (!quit) {
                System.out.println("Starting scan for expired coupons");
                this.couponDAO.deleteExpiredCoupons();
                System.out.println("Going to sleep");
                Thread.sleep(1000 * 60 * 60 * 24);
            }
        } catch (InterruptedException e) {
            System.out.println("I'm up !");
        }
        System.out.println("I'm out");
    }
}
