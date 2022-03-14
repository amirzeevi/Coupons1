import beans.Category;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import dbdao.CompaniesDBDAO;
import exceptions.CouponsSystemException;
import facade.AdminFacade;
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
 * tables are created in the {@link TablesTest} test class and to fill the categories table
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
            Coupon existsCoupon = companyFacade.getCompanyCoupons().get(0);
            existsCoupon.setTitle("Hotel California");
            companyFacade.updateCoupon(existsCoupon);
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
    public void getCompanyCoupons() {
        try {
            TablePrinter.print(companyFacade.getCompanyCoupons());
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getCompanyCouponsCategory() {
        try {
            TablePrinter.print(companyFacade.getCompanyCoupons(Category.FOOD));
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getCompanyCouponsMaxPrice() {
        try {
            TablePrinter.print(companyFacade.getCompanyCoupons(20));
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCompanyDetails() {
        TablePrinter.print(companyFacade.getCompanyDetails());
    }
}


