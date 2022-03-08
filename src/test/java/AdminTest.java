import beans.ClientType;
import beans.Company;
import beans.Customer;

import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.LoginManager;
import org.junit.Test;
import utils.TablePrinter;



/**
 * Create schema and tables in {@link Program
 */

public class AdminTest {

    AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);

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
            Company companyToAdd = new Company(0, "Second Company", "company2@com", "1234", null);
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
            companyExists.setEmail("company2@com");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCompany() {
        try {
            adminFacade.deleteCompany(7);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllCompanies() {
        try {
            TablePrinter.print(adminFacade.getAllCompanies());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetOneCompany() {
        try {
            System.out.println(adminFacade.getOneCompany(9));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCustomer() {
        try {
            Customer customerToAdd = new Customer(0, "Ploni", "Israeli", "my.email@com", "1234", null);
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
            customerExists.setPassword("password");
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
        try {
            TablePrinter.print(adminFacade.getAllCustomers());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetOneCustomer() {
        try {
            System.out.println(adminFacade.getOneCustomer(0));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}
