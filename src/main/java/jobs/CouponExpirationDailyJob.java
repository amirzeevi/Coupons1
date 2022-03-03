package jobs;

import DBdao.CouponDBDAO;
import dao.CouponsDAO;

import java.sql.Date;
import java.time.LocalDate;


public class CouponExpirationDailyJob implements Runnable {

    private CouponsDAO couponDAO;
    private boolean quit;

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
                this.couponDAO.getAllCoupons().stream()
                        .filter(coupon -> coupon.getEndDate().before(Date.valueOf(LocalDate.now())))
                        .forEach(coupon -> this.couponDAO.deleteCoupon(coupon.getId()));

                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
