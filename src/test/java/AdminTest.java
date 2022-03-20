import beans.ClientType;
import beans.Company;
import beans.Customer;
import exceptions.CouponsSystemException;
import facade.AdminFacade;
import facade.LoginManager;
import org.junit.jupiter.api.*;
import utils.Art;
import utils.TablePrinter;

/**
 * Test class for the {@link AdminFacade} class methods. Before testing make sure the schema and tables are created
 * in the {@link TablesTest} class.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminTest {
    AdminFacade adminFacade;

    @BeforeAll
    static void beforeAll() {
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
                    "Company",
                    "company@com",
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
    public void addCompany2() {
        System.out.println("TESTING ADDING SECOND COMPANY");
        try {
            Company companyToAdd = new Company(
                    "Company2",
                    "company2@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(5)
    public void addCompanyFail() {
        System.out.println("TESTING ADD COMPANY NAME FAIL");
        try {
            Company companyToAdd = new Company(
                    "Company",
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
    @Order(6)
    public void addCompanyFail2() {
        System.out.println("TESTING ADD COMPANY EMAIL FAIL");

        try {
            Company companyToAdd = new Company(
                    "Company3",
                    "company2@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    @Test
    @Order(7)
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
    @Order(8)
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
    @Order(9)
    public void updateCompanyFail2() {
        System.out.println("TESTING UPDATE COMPANY EMAIL FAIL");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setEmail("company2@com");
            adminFacade.updateCompany(companyExists);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(10)
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
    @Order(11)
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
    @Order(12)
    public void getAllCompanies() {
        System.out.println("TESTING GET ALL COMPANIES");
        TablePrinter.print(adminFacade.getAllCompanies());
        System.out.println();

    }

    @Test
    @Order(13)
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
    @Order(14)
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
    @Order(15)
    public void addCustomer() {
        System.out.println("TESTING ADD CUSTOMER");
        try {
            Customer customerToAdd = new Customer(
                    "Ploni",
                    "Israeli",
                    "my.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(16)
    public void addCustomer2() {
        System.out.println("TESTING ADD SECOND CUSTOMER");
        try {
            Customer customerToAdd = new Customer(
                    "Almoni",
                    "Israeli",
                    "my.email2@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            Assertions.fail();
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(17)
    public void addCustomerFail() {
        System.out.println("TESTING ADD CUSTOMER EMAIL FAIL");
        try {
            Customer customerToAdd = new Customer(
                    "Gilad",
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
    @Order(18)
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
    @Order(19)
    public void updateCustomerFail() {
        System.out.println("TESTING UPDATE CUSTOMER EMAIL FAIL");
        try {
            Customer customerExists = adminFacade.getOneCustomer(1);
            customerExists.setEmail("my.email2@com");
            adminFacade.updateCustomer(customerExists);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    @Test
    @Order(20)
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
    @Order(21)
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
    @Order(22)
    public void getAllCustomers() {
        System.out.println("TESTING GET ALL CUSTOMERS");
        TablePrinter.print(adminFacade.getAllCustomers());
    }

    @Test
    @Order(23)
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
    @Order(24)
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
}
