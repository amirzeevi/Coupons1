import beans.Category;
import beans.ClientType;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.LogInException;
import exceptions.NotAllowedValueChange;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {

    CompanyFacade companyFacade;

    @Before
    public void setUp() {
        companyFacade = new CompanyFacade();
        companyFacade.login("company@com", "password");
    }

    @After
    public void tearDown() {
        companyFacade = null;
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("company@com", "password", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = LogInException.class)
    public void exceptionLogin() throws LogInException {
        LoginManager.getInstance().login("company@com", "not.remember", ClientType.COMPANY);
    }

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(
                    0,
                    1,
                    Category.ELECTRICITY,
                    "Electric Bike",
                    "myDescription",
                    Date.valueOf(LocalDate.now()),
                    Date.valueOf(LocalDate.now().plusDays(12)),
                    20,
                    99.99,
                    "image");

            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCoupon2() {
        try {
            Coupon couponToAdd = new Coupon(
                    0,
                    1,
                    Category.VACATION,
                    "Hotel California",
                    "Vacation",
                    Date.valueOf(LocalDate.now()),
                    Date.valueOf(LocalDate.now().plusDays(12)),
                    20,
                    99.99,
                    "image");

            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionAddCoupon() throws CouponSystemException {
        Coupon couponToAdd = new Coupon(
                0,
                4,
                Category.ELECTRICITY,
                "Electric Bike",
                "myDescription",
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(12)),
                20,
                99.99,
                "image");

        companyFacade.addCoupon(couponToAdd);
    }

    @Test
    public void UpdateCoupon() {
        try {
            Coupon existsCoupons = companyFacade.getCompanyCoupons().get(0);
            existsCoupons.setTitle("City Bike");
            companyFacade.updateCoupon(existsCoupons);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionUpdateCoupon() throws CouponSystemException{
        Coupon existsCoupons = companyFacade.getCompanyCoupons().get(1);
        existsCoupons.setAmount(-5);
        companyFacade.updateCoupon(existsCoupons);
    }

    @Test
    public void DeleteCoupon() {
        try {
            companyFacade.deleteCoupon(2);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionDeleteCoupon() throws CouponSystemException{
        companyFacade.deleteCoupon(23);
    }

    @Test
    public void getOneCoupon() {
        try {
            TablePrinter.print(companyFacade.getOneCoupon(1));
        } catch (CouponSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionGetOneCoupon() throws CouponSystemException {
        TablePrinter.print(companyFacade.getOneCoupon(4));
    }

    @Test
    public void getCompanyCoupons() {
        //takes arguments
        TablePrinter.print(companyFacade.getCompanyCoupons());
    }

    @Test
    public void GetCompanyDetails() {
        TablePrinter.print(companyFacade.getCompanyDetails());
    }
}


