package jobs;

import dbdao.CouponsDBDAO;
import dao.CouponsDAO;

/**
 * The class implements the {@link Runnable} interface. it should run as a separate thread in the Coupon System.
 * Its single purpose is to perform a check once every 24 hours and delete from the database any coupon that has expired.
 */
public class CouponExpirationDailyJob implements Runnable {
    private final CouponsDAO couponDAO;
    private boolean quit;

    /**
     * Constructs the class and initiates the {@link CouponsDAO} and sets the boolean quit to false.
     * Is called once when the system goes up.
     */
    public CouponExpirationDailyJob() {
        this.couponDAO = new CouponsDBDAO();
        this.quit = false;
    }

    /**
     * Sets the boolean quit.
     */
    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    /**
     * A method that class the setQuit method for when trying to stop the while loop
     * in the run method causing the run to finish.
     */
    public void stop() {
        setQuit(true);
    }

    /**
     * The only method overridden from the {@link Runnable} interface. This will run as a separate thread in a loop
     * as long as the system is running.
     */
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
            System.out.println("I'm up");
        }
        System.out.println("I'm out");
    }
}
