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

    @Test(expected = CouponSystemException.class)
    public void exceptionAddCompany() throws CouponSystemException {
        Company companyToAdd = new Company(
                0,
                "Company3",
                "company@com",
                "1234",
                new ArrayList<>());
        adminFacade.addCompany(companyToAdd);
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

    @Test(expected = NotAllowedValueChange.class)
    public void exceptionUpdateCompany() throws NotAllowedValueChange, CouponSystemException {
        Company companyExists = adminFacade.getOneCompany(1);
        companyExists.setId(1);
        adminFacade.updateCompany(companyExists);

    }

    @Test(expected = CouponSystemException.class)
    public void exceptionUpdateCompany2() throws CouponSystemException {
        Company companyExists = adminFacade.getOneCompany(1);
        companyExists.setEmail("company2@com");
        adminFacade.updateCompany(companyExists);
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

    @Test(expected = CouponSystemException.class)
    public void exceptionDeleteCompany() throws CouponSystemException{
        adminFacade.deleteCompany(2);
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

    @Test(expected = CouponSystemException.class)
    public void exceptionGetOneCompany() throws CouponSystemException{
        TablePrinter.print(adminFacade.getOneCompany(2));
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

    @Test(expected = CouponSystemException.class)
    public void exceptionAddCustomer() throws CouponSystemException {
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
            customerExists.setPassword("12345678");
            adminFacade.updateCustomer(customerExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = NotAllowedValueChange.class)
    public void exceptionUpdateCustomer() throws CouponSystemException, NotAllowedValueChange {

        Customer customerExists = adminFacade.getAllCustomers().get(0);
        customerExists.setId(14);
        adminFacade.updateCustomer(customerExists);

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

    @Test(expected = CouponSystemException.class)
    public void exceptionDeleteCustomer() throws CouponSystemException {
        adminFacade.deleteCustomer(2);
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

    @Test(expected = CouponSystemException.class)
    public void exceptionGetOneCustomer() throws CouponSystemException{
        TablePrinter.print(adminFacade.getOneCustomer(123));
    }
}
