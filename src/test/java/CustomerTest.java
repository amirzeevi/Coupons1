import beans.*;
import db.DBmanager;
import db.DBrunQuery;
import dbdao.CategoriesDBDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import org.junit.jupiter.api.*;
import utils.Art;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Test class for the {@link CustomerFacade} class methods.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CustomerTest {
    CustomerFacade customerFacade;
    Customer customer;
    @BeforeAll
    static void beforeAll() {
        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.SET_TIME_ZONE);
        DBrunQuery.runQuery(DBmanager.CREATE_TRIGGER_COUPON_PURCHASE);
        new CategoriesDBDAO().addAllCategories();
        new CustomersDBDAO().addCustomer(new Customer(
                "Almoni",
                "Israeli",
                "my.email@com",
                "1234"));
        new CompaniesDBDAO().addCompany(new Company(
                "Company",
                "company@com",
                "1234"));
        new CouponsDBDAO().addCoupon(new Coupon(
                1,
                Category.ELECTRICITY,
                "Electric Bike",
                "myDescription",
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(12)),
                20,
                99.99,
                "image"));
        Art.cutomerArt();
        System.out.println();
    }
    @BeforeEach
    public void setUp() {
        customer = new CustomersDBDAO().getAllCustomers().get(0);
        customerFacade = new CustomerFacade();
        customerFacade.login(customer.getEmail(), customer.getPassword());
    }

    @AfterEach
    public void tearDown() {
        customerFacade = null;
        customer = null;
    }

    @Test
    @Order(1)
    public void login() {
        try {
            System.out.println("TESTING LOGIN");
            LoginManager.getInstance().login(customer.getEmail(), customer.getPassword(), ClientType.CUSTOMER);
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
            LoginManager.getInstance().login("my.email@com", "012345678", ClientType.CUSTOMER);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(3)
    public void purchase() {
        System.out.println("TESTING PURCHASE");
        try {
            Coupon coupon = new CouponsDBDAO().getOneCoupon(1);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(4)
    public void purchaseFail() {
        System.out.println("TESTING PURCHASE FAIL");
        try {
            Coupon coupon = new CouponsDBDAO().getOneCoupon(1);
            customerFacade.purchaseCoupon(coupon);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(5)
    public void getAllPurchased() {
        System.out.println("TESTING GET ALL PURCHASED");
        TablePrinter.print(customerFacade.getCustomerCoupons());
        System.out.println();
    }

    @Test
    @Order(6)
    public void getAllPurchasedCategory() {
        System.out.println("TESTING GET PURCHASED BY CATEGORY");
        TablePrinter.print(customerFacade.getCustomerCoupons(Category.ELECTRICITY));
        System.out.println();
    }

    @Test
    @Order(7)
    public void getAllPurchasedMaxPrice() {
        System.out.println("TESTING GET PURCHASED BY MAX PRICE");
        TablePrinter.print(customerFacade.getCustomerCoupons(100));
        System.out.println();
    }

    @Test
    @Order(8)
    public void getCostumerDetails() {
        System.out.println("TESTING GET CUSTOMER DETAILS");
        TablePrinter.print(customerFacade.getCustomerDetails());
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_SCHEMA);

    }
}
