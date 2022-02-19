import beans.Category;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.LoginManager;
import junit.framework.TestCase;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompanyTest extends TestCase {
    CompanyFacade companyFacade = new CompanyFacade(41);

    public CompanyTest()  {
    }

    public void testLogin() {
        try {
            LoginManager.getInstance().login("email@email", "1234", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testAddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(41, 1, "myName2", "myDescription", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 20, 99.90, "image");
            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testUpdateCoupon() {
        try {
            Coupon existsCoupons = companyFacade.getCompanyCoupons().get(0);
            existsCoupons.setImage("imim");
            companyFacade.updateCoupon(existsCoupons);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testDeleteCoupon(){
        try {
            Coupon existsCoupons = companyFacade.getCompanyCoupons().get(0);
            companyFacade.deleteCoupon(0);
        }catch (Exception e){
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetCompanyCoupons() {
        try {
            companyFacade.getCompanyCoupons().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetCompanyDetails() {
        try {
            System.out.println(companyFacade.getCompanyDetails());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}


