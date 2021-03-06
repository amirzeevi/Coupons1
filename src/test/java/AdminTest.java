import beans.ClientType;
import beans.Company;
import beans.Customer;
import db.DBmanager;
import db.DBrunQuery;
import dbdao.CategoriesDBDAO;
import exceptions.CouponsSystemException;
import facade.AdminFacade;
import facade.LoginManager;
import org.junit.jupiter.api.*;
import utils.Art;
import utils.TablePrinter;

/**
 * Test class for the {@link AdminFacade} class methods.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminTest {
    AdminFacade adminFacade;

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
        Art.adminArt();
        System.out.println();
    }

    @BeforeEach
    public void setUp() {
        adminFacade = new AdminFacade();
        adminFacade.login("admin@admin", "admin");
    }

    @AfterEach
    public void tearDown() {
        adminFacade = null;
    }

    @Test
    @Order(1)
    public void login() {
        System.out.println("TESTING LOGIN");
        try {
            LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
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
            LoginManager.getInstance().login("Xadmin@admin", "admin", ClientType.ADMINISTRATOR);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(3)
    public void addCompany() {
        System.out.println("TESTING ADD COMPANY");
        try {
            Company companyToAdd = new Company(
                    "Fresh Clothing",
                    "fresh@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            companyToAdd = new Company(
                    "Let's go Sailing Trips",
                    "sail@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            companyToAdd = new Company(
                    "Macdonald Burger",
                    "mac@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            companyToAdd = new Company(
                    "Intel Corp",
                    "intel@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            companyToAdd = new Company(
                    "Dayson Vacuum Cleaner",
                    "dye@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(4)
    public void addCompanyFail() {
        System.out.println("TESTING ADD COMPANY NAME FAIL");
        try {
            Company companyToAdd = new Company(
                    "Intel Corp",
                    "company3@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(5)
    public void addCompanyFail2() {
        System.out.println("TESTING ADD COMPANY EMAIL FAIL");

        try {
            Company companyToAdd = new Company(
                    "Company3",
                    "fresh@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(6)
    public void updateCompany() {
        System.out.println("TESTING UPDATE COMPANY");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setEmail("New@com");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(7)
    public void updateCompanyFail() {
        System.out.println("TESTING UPDATE COMPANY NAME FAIL");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setName("john");
            adminFacade.updateCompany(companyExists);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(8)
    public void updateCompanyFail2() {
        System.out.println("TESTING UPDATE COMPANY EMAIL FAIL");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setEmail("sail@com");
            adminFacade.updateCompany(companyExists);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(9)
    public void deleteCompany() {
        System.out.println("TESTING DELETE COMPANY");
        try {
            adminFacade.deleteCompany(2);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(10)
    public void deleteCompanyFail() {
        System.out.println("TESTING DELETE COMPANY FAIL");
        try {
            adminFacade.deleteCompany(1000);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(11)
    public void getAllCompanies() {
        System.out.println("TESTING GET ALL COMPANIES");
        TablePrinter.print(adminFacade.getAllCompanies());
        System.out.println();

    }

    @Test
    @Order(12)
    public void getOneCompany() {
        System.out.println("TESTING GET ONE COMPANY");
        try {
            TablePrinter.print(adminFacade.getOneCompany(1));
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(13)
    public void getOneCompanyFail() {
        System.out.println("TESTING GET ONE COMPANY FAIL");
        try {
            TablePrinter.print(adminFacade.getOneCompany(1000));
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(14)
    public void addCustomer() {
        System.out.println("TESTING ADD CUSTOMER");
        try {
            Customer customerToAdd = new Customer(
                    "Ploni",
                    "Israeli",
                    "my.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
            customerToAdd = new Customer(
                    "Almoni",
                    "Israeli",
                    "israeli.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
            customerToAdd = new Customer(
                    "Gilad",
                    "Armon",
                    "gilad.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
            customerToAdd = new Customer(
                    "Roei",
                    "Bond",
                    "roi.mail@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
            customerToAdd = new Customer(
                    "Matan",
                    "Yael",
                    "matan.mail@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(15)
    public void addCustomerFail() {
        System.out.println("TESTING ADD CUSTOMER EMAIL FAIL");
        try {
            Customer customerToAdd = new Customer(
                    "Shlomo",
                    "Israeli",
                    "my.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(16)
    public void updateCustomer() {
        System.out.println("TESTING UPDATE CUSTOMER");
        try {
            Customer customerExists = adminFacade.getOneCustomer(1);
            customerExists.setPassword("12345678");
            adminFacade.updateCustomer(customerExists);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(17)
    public void updateCustomerFail() {
        System.out.println("TESTING UPDATE CUSTOMER EMAIL FAIL");
        try {
            Customer customerExists = adminFacade.getOneCustomer(1);
            customerExists.setEmail("matan.mail@com");
            adminFacade.updateCustomer(customerExists);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(18)
    public void deleteCustomer() {
        System.out.println("TESTING DELETE CUSTOMER");
        try {
            adminFacade.deleteCustomer(2);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(19)
    public void deleteCustomerFail() {
        System.out.println("TESTING DELETE CUSTOMER FAIL");
        try {
            adminFacade.deleteCustomer(2);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(20)
    public void getAllCustomers() {
        System.out.println("TESTING GET ALL CUSTOMERS");
        TablePrinter.print(adminFacade.getAllCustomers());
    }

    @Test
    @Order(21)
    public void getOneCustomer() {
        System.out.println("TESTING GET ONE CUSTOMER");
        try {
            TablePrinter.print(adminFacade.getOneCustomer(1));
        } catch (CouponsSystemException e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(22)
    public void getOneCustomerFail() {
        System.out.println("TESTING GET ONE CUSTOMER FAIL");
        try {
            TablePrinter.print(adminFacade.getOneCustomer(1000));
            Assertions.fail();
        } catch (CouponsSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        DBrunQuery.runQuery(DBmanager.DROP_SCHEMA);
    }
}
