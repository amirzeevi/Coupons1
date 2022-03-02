package jobs;

import DBdao.CouponDBDAO;
import beans.Coupon;
import dao.CouponsDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDAO couponDAO;
    private boolean quit;

    public CouponExpirationDailyJob(CouponDBDAO couponDAO) {
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
