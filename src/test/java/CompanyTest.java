import beans.Category;
import beans.ClientType;
import beans.Coupon;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.Test;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {

    CompanyFacade companyFacade = new CompanyFacade(7);

    public CompanyTest() {
    }

    @Test
    public void Login() {
        try {
            //need to also change company id in Login Manager
            LoginManager.getInstance().login("my.email2@com", "1234", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(0, 7, Category.FOOD.value, "myName2", "myDescription", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 20, 99.90, "image");
            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdateCoupon() {
        try {
            Coupon existsCoupons = companyFacade.getCompanyCoupons().get(0);
            existsCoupons.setDescription("sidsid");
            companyFacade.updateCoupon(existsCoupons);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCoupon() {
        try {
            companyFacade.deleteCoupon(7);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getOneCoupon() {
        try {
            System.out.println(companyFacade.getOneCoupon(4));
        } catch (CouponSystemException e) {
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


