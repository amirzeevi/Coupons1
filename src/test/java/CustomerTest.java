import beans.Category;
import beans.ClientType;
import beans.Coupon;
import beans.Customer;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import org.junit.jupiter.api.*;
import utils.Art;
import utils.TablePrinter;

/**
 * Test class for the {@link CompanyFacade} class methods. Before testing make sure to fill the categories table
 * using the {@link CategoryTest} test class.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CustomerTest {
    CustomerFacade customerFacade;
    Customer customer;

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

    @BeforeAll
    static void beforeAll() {
        Art.cutomerArt();
        System.out.println();
    }
}
