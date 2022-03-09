import beans.Category;
import beans.ClientType;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.LogInException;
import facade.ClientFacade;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {

    CompanyFacade companyFacade;

    @Before
    public void setUp() {
        try {
            companyFacade = (CompanyFacade) LoginManager.getInstance().
                    login("com", "cm", ClientType.COMPANY);
        } catch (LogInException e) {
            System.out.println(e.getMessage());
            companyFacade = new CompanyFacade();
        }
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

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(0,
                    6,
                    Category.ELECTRICITY,
                    "Electric Bik23e",
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
    public void UpdateCoupon() {
        try {
            Coupon existsCoupons = companyFacade.getCompanyCoupons().get(1);
            existsCoupons.setPrice(20);
            companyFacade.updateCoupon(existsCoupons);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCoupon() {
        try {
            companyFacade.deleteCoupon(18);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getOneCoupon() {
        try {
            TablePrinter.print(companyFacade.getOneCoupon(19));
        } catch (CouponSystemException | LogInException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getCompanyCoupons() {
        try {
            //accepts arguments
            TablePrinter.print(companyFacade.getCompanyCoupons());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCompanyDetails() {
        try {
            System.out.println(companyFacade.getCompanyDetails());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}


