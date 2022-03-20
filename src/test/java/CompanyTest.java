import beans.Category;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import dbdao.CompaniesDBDAO;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Test class for the {@link CompanyFacade} class methods. Before testing make sure the schema and all
 * tables are created in the {@link TablesTest}  class, and to fill the categories table
 * using the {@link CategoryTest} test class.
 */
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
        company = null;
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("company2@com", "Xpassword", ClientType.COMPANY);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCoupon() {
        try {
            Coupon couponToAdd = new Coupon(
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
                    company.getId(),
                    Category.VACATION,
                    "Hotel California",
                    "Vacation",
                    Date.valueOf(LocalDate.now()),
                    Date.valueOf(LocalDate.now().plusDays(12)),
                    20,
                    199.99,
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
            Coupon existsCoupon = companyFacade.getCompanyCoupons().get(1);
            existsCoupon.setEndDate(Date.valueOf(LocalDate.now().minusDays(20)));
            companyFacade.updateCoupon(existsCoupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCoupon() {
        try {
            companyFacade.deleteCoupon(17);
        } catch (Exception e) {
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
        TablePrinter.print(companyFacade.getCompanyCoupons(Category.ELECTRICITY));
    }

    @Test
    public void getCompanyCouponsMaxPrice() {
        TablePrinter.print(companyFacade.getCompanyCoupons(100));
    }

    @Test
    public void GetCompanyDetails() {
        TablePrinter.print(companyFacade.getCompanyDetails());
    }
}


