import beans.Category;
import beans.ClientType;
import beans.Company;
import beans.Coupon;
import dbdao.CompaniesDBDAO;
import facade.CompanyFacade;
import facade.LoginManager;
import org.junit.jupiter.api.*;
import utils.Art;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Test class for the {@link CompanyFacade} class methods. Before testing make sure the schema and all
 * tables are created in the {@link TablesTest}  class.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyTest {
    Company company;
    CompanyFacade companyFacade;

    @BeforeAll
    static void beforeAll() {
        Art.companyArt();
        System.out.println();
    }

    @BeforeEach
    public void setUp() {
        company = new CompaniesDBDAO().getAllCompanies().get(0);
        companyFacade = new CompanyFacade();
        companyFacade.login(company.getEmail(), company.getPassword());
    }

    @AfterEach
    public void tearDown() {
        companyFacade = null;
        company = null;
    }

    @Test
    @Order(1)
    public void login() {
        System.out.println("TESTING LOGIN");
        try {
            LoginManager.getInstance().login(company.getEmail(), company.getPassword(), ClientType.COMPANY);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(2)
    public void loginFail() {
        System.out.println("TESTING LOGIN FAIL");
        try {
            LoginManager.getInstance().login("company2@com", "Xpassword", ClientType.COMPANY);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(3)
    public void addCoupon() {
        System.out.println("TEST ADD COUPON");
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
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(5)
    public void addCoupon2() {
        System.out.println("TESTING ADD SECOND COUPON");
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
            System.out.println(e.getMessage());
            Assertions.fail();
        }
        System.out.println();
    }

    @Test
    @Order(6)
    public void addCouponFail() {
        System.out.println("TESTING ADD COUPON FAIL");
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
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(7)
    public void updateCoupon() {
        System.out.println("TESTING UPDATE COUPON");
        try {
            Coupon existsCoupon = companyFacade.getCompanyCoupons().get(1);
            existsCoupon.setPrice(120.0);
            companyFacade.updateCoupon(existsCoupon);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(8)
    public void updateCouponFail() {
        System.out.println("TESTING UPDATE COUPON FAIL");
        try {
            Coupon existsCoupon = companyFacade.getCompanyCoupons().get(1);
            existsCoupon.setEndDate(Date.valueOf(LocalDate.now().minusDays(20)));
            companyFacade.updateCoupon(existsCoupon);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(9)
    public void deleteCoupon() {
        System.out.println("TESTING DELETE COUPON");
        try {
            companyFacade.deleteCoupon(2);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(10)
    public void deleteCouponFail() {
        System.out.println("TESTING DELETE COUPON FAIL");
        try {
            companyFacade.deleteCoupon(17);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(11)
    public void getCompanyCoupons() {
        System.out.println("TESTING GET COMPANY COUPONS");
        TablePrinter.print(companyFacade.getCompanyCoupons());
        System.out.println();
    }

    @Test
    @Order(12)
    public void getCompanyCouponsCategory() {
        System.out.println("TESTING GET COMPANY COUPONS BY CATEGORY");
        TablePrinter.print(companyFacade.getCompanyCoupons(Category.ELECTRICITY));
        System.out.println();
    }

    @Test
    @Order(13)
    public void getCompanyCouponsMaxPrice() {
        System.out.println("TESTING GET COMPANY COUPONS BY MAX PRICE");
        TablePrinter.print(companyFacade.getCompanyCoupons(100));
        System.out.println();
    }

    @Test
    @Order(14)
    public void getCompanyDetails() {
        System.out.println("TESTING GET COMPANY DETAILS");
        TablePrinter.print(companyFacade.getCompanyDetails());
    }
}


