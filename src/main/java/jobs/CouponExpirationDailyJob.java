package jobs;

import DBdao.CouponDBDAO;
import beans.Coupon;
import exceptions.CouponSystemException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CouponExpirationDailyJob implements Runnable {
    private CouponDBDAO couponDAO;
    private boolean quit;

    public CouponExpirationDailyJob(CouponDBDAO couponDAO) {
        this.couponDAO = couponDAO;
        this.quit = false;
    }

    public CouponDBDAO getCouponDAO() {
        return couponDAO;
    }

    public void setCouponDAO(CouponDBDAO couponDAO) {
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
                List<Coupon> couponToDelete = this.couponDAO.getAllCoupons().stream().filter(coupon -> coupon.getEndDate().after(Date.valueOf(LocalDate.now()))).collect(Collectors.toList());
                for (Coupon coupon : couponToDelete) {
                    this.couponDAO.deleteCouponPurchase(0, coupon.getId());
                    this.couponDAO.deleteCoupon(coupon.getId());
                }
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
