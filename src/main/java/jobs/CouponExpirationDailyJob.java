package jobs;

import dbdao.CouponDBDAO;
import dao.CouponsDAO;


public class CouponExpirationDailyJob implements Runnable {

    private CouponsDAO couponDAO;
    private boolean quit = false;

    public CouponExpirationDailyJob() {
        this.couponDAO = new CouponDBDAO();
        this.quit = false;
    }

    public CouponsDAO getCouponDAO() {
        return couponDAO;
    }

    public void setCouponDAO(CouponsDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public void stop() {
        setQuit(true);
    }

    @Override
    public void run() {

        while (!quit) {
            try {

                this.couponDAO.deleteExpiredCoupons();

                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
