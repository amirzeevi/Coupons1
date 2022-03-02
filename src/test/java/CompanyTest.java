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

    CompanyFacade companyFacade = new CompanyFacade(4);

    public CompanyTest() {
    }

    @Test
    public void Login() {
        try {
            //need to also add company id in Login Manager
            LoginManager.getInstance().login("my.email@com", "1234", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(0, 4, Category.ELECTRICITY.value, "Electric2 Bike", "myDescription", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(14)), 20, 99.99, "image");
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
            existsCoupons.setStartDate(Date.valueOf(LocalDate.now().plusDays(20)));
            companyFacade.updateCoupon(existsCoupons);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCoupon() {
        try {
            companyFacade.deleteCoupon(3);
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


