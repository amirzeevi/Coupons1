import beans.ClientType;
import beans.Company;
import beans.Customer;
import facade.AdminFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

import java.util.ArrayList;


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
                    0,
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
                    0,
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
            companyExists.setPassword("password");
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
        TablePrinter.print(adminFacade.getAllCompanies());
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
                    0,
                    "Ploni",
                    "Israeli",
                    "my.email@com",
                    "1234",
                    new ArrayList<>());
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
                    0,
                    "Almoni",
                    "Israeli",
                    "my.email2@com",
                    "1234",
                    new ArrayList<>());
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdateCustomer() {
        try {
            Customer customerExists = adminFacade.getAllCustomers().get(0);
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
            adminFacade.deleteCustomer(2);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllCustomers() {
        TablePrinter.print(adminFacade.getAllCustomers());
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
