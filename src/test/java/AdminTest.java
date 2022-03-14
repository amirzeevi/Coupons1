import beans.ClientType;
import beans.Company;
import beans.Customer;
import exceptions.CouponsSystemException;
import facade.AdminFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

import java.util.ArrayList;

/**
 * Test class for the {@link AdminFacade} class methods. Before testing make sure the schema and tables are created
 * in the {@link TablesTest} test class.
 */
public class AdminTest {

    AdminFacade adminFacade;

    @Before
    public void setUp() {
        adminFacade = new AdminFacade();
        adminFacade.login("admin@admin", "admin");
    }

    @After
    public void tearDown() {
        adminFacade = null;
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCompany() {
        try {
            Company companyToAdd = new Company(
                    "Company",
                    "company@com",
                    "1234",
                    new ArrayList<>());
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCompany2() {
        try {
            Company companyToAdd = new Company(
                    "Company2",
                    "company2@com",
                    "1234",
                    new ArrayList<>());
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdateCompany() {
        try {
            Company companyExists = adminFacade.getAllCompanies().get(0);
            companyExists.setEmail("njnjnj");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCompany() {
        try {
            adminFacade.deleteCompany(2);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllCompanies() {
        try {
            TablePrinter.print(adminFacade.getAllCompanies());
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetOneCompany() {
        try {
            TablePrinter.print(adminFacade.getOneCompany(1));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCustomer() {
        try {
            Customer customerToAdd = new Customer(
                    "Ploni",
                    "Israeli",
                    "my.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCustomer2() {
        try {
            Customer customerToAdd = new Customer(
                    "Almoni",
                    "Israeli",
                    "my.email2@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdateCustomer() {
        try {
            Customer customerExists = adminFacade.getOneCustomer(1);
            customerExists.setPassword("12345678");
            adminFacade.updateCustomer(customerExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCustomer() {
        try {
            adminFacade.deleteCustomer(3);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllCustomers() {
        try {
            TablePrinter.print(adminFacade.getAllCustomers());
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetOneCustomer() {
        try {
            TablePrinter.print(adminFacade.getOneCustomer(1));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}
