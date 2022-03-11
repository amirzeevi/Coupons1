import beans.ClientType;
import beans.Company;
import beans.Customer;

import exceptions.NotAllowedValueChange;
import exceptions.CouponSystemException;
import exceptions.LogInException;
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

    @Test(expected = LogInException.class)
    public void exceptionLogin() throws LogInException {
        LoginManager.getInstance().login("not@true", "2022", ClientType.ADMINISTRATOR);
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

    @Test(expected = CouponSystemException.class)
    public void exceptionAddCompany() throws CouponSystemException, LogInException {
        Company companyToAdd = new Company(
                0,
                "New Company",
                "company@com",
                "1234",
                new ArrayList<>());
        adminFacade.addCompany(companyToAdd);
    }

    @Test
    public void UpdateCompany() {
        try {
            Company companyExists = adminFacade.getAllCompanies().get(0);
            companyExists.setPassword("Eded");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = NotAllowedValueChange.class)
    public void exceptionUpdateCompany() throws NotAllowedValueChange, LogInException, CouponSystemException {
        Company companyExists = adminFacade.getOneCompany(2);
        companyExists.setId(1);
        adminFacade.updateCompany(companyExists);

    }

    @Test(expected = CouponSystemException.class)
    public void exceptionUpdateCompany2() throws LogInException, CouponSystemException {
        Company companyExists = adminFacade.getOneCompany(2);
        companyExists.setEmail("company@com");
        adminFacade.updateCompany(companyExists);
    }

    @Test
    public void DeleteCompany() {
        try {
            adminFacade.deleteCompany(1);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionDeleteCompany() throws CouponSystemException, LogInException {
        adminFacade.deleteCompany(1);
    }

    @Test
    public void GetAllCompanies() throws LogInException {
        TablePrinter.print(adminFacade.getAllCompanies());
    }

    @Test
    public void GetOneCompany() {
        try {
            TablePrinter.print(adminFacade.getOneCompany(3));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionGetOneCompany() throws CouponSystemException, LogInException {
        TablePrinter.print(adminFacade.getOneCompany(1));
    }

    @Test
    public void AddCustomer() {
        try {
            Customer customerToAdd = new Customer(
                    0,
                    "Ploni",
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

    @Test(expected = CouponSystemException.class)
    public void exceptionAddCustomer() throws CouponSystemException, LogInException {
        Customer customerToAdd = new Customer(
                0,
                "Almoni",
                "Israeli",
                "my.email@com",
                "1234",
                new ArrayList<>());
        adminFacade.addCustomer(customerToAdd);
    }

    @Test
    public void UpdateCustomer() {
        try {
            Customer customerExists = adminFacade.getAllCustomers().get(0);
            customerExists.setPassword("1234");
            adminFacade.updateCustomer(customerExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = NotAllowedValueChange.class)
    public void exceptionUpdateCustomer() throws CouponSystemException, LogInException, NotAllowedValueChange {

        Customer customerExists = adminFacade.getAllCustomers().get(0);
        customerExists.setId(14);
        adminFacade.updateCustomer(customerExists);

    }

    @Test
    public void DeleteCustomer() {
        try {
            adminFacade.deleteCustomer(1);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionDeleteCustomer() throws CouponSystemException, LogInException {
        adminFacade.deleteCustomer(1);
    }

    @Test
    public void GetAllCustomers() throws LogInException {
        TablePrinter.print(adminFacade.getAllCustomers());
    }

    @Test
    public void GetOneCustomer() {
        try {
            TablePrinter.print(adminFacade.getOneCustomer(2));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }


    @Test(expected = CouponSystemException.class)
    public void exceptionGetOneCustomer() throws CouponSystemException, LogInException {
        TablePrinter.print(adminFacade.getOneCustomer(123));
    }
}
