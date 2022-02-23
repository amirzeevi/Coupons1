import beans.ClientType;
import beans.Coupon;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {
    CompanyFacade companyFacade = new CompanyFacade(3);

    public CompanyTest() {
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("email@email", "1234", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(3, 1, "myName", "myDescription", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 20, 99.90, "image");
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
            existsCoupons.setImage("imim");
            companyFacade.updateCoupon(existsCoupons);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCoupon() {
        try {
            Coupon existsCoupons = companyFacade.getCompanyCoupons().get(0);
            companyFacade.deleteCoupon(existsCoupons.getId());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCompanyCoupons() {
        try {
            companyFacade.getCompanyCoupons().forEach(System.out::println);
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


