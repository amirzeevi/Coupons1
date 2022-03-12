import beans.Category;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import dbdao.CompaniesDBDAO;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {
    Company company;
    CompanyFacade companyFacade;

    @Before
    public void setUp() {
        company = new CompaniesDBDAO().getAllCompanies().get(0);
        companyFacade = new CompanyFacade();
        companyFacade.login(company.getEmail(), company.getPassword());
    }

    @After
    public void tearDown() {
        companyFacade = null;
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("company2@com", "password", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(
                    0,
                    company.getId(),
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
                    company.getId(),
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

    @Test
    public void DeleteCoupon() {
        try {
            companyFacade.deleteCoupon(2);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
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

    @Test
    public void getCompanyCoupons() {
        TablePrinter.print(companyFacade.getCompanyCoupons());
    }

    @Test
    public void getCompanyCouponsCategory() {
        TablePrinter.print(companyFacade.getCompanyCoupons(Category.FOOD));
    }
    @Test
    public void getCompanyCouponsMaxPrice() {
        TablePrinter.print(companyFacade.getCompanyCoupons(20));
    }

    @Test
    public void GetCompanyDetails() {
        TablePrinter.print(companyFacade.getCompanyDetails());
    }
}


