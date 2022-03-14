import beans.Category;
import beans.ClientType;
import beans.Coupon;
import beans.Customer;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import exceptions.CouponsSystemException;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Test class for the {@link CompanyFacade} class methods. Before testing make sure to fill the categories table
 * using the {@link CategoryTest} test class.
 */
public class CustomerTest {

    CustomerFacade customerFacade;
    Customer customer;

    @Before
    public void setUp() {
        customer = new CustomersDBDAO().getAllCustomers().get(0);
        customerFacade = new CustomerFacade();
        customerFacade.login(customer.getEmail(), customer.getPassword());
    }

    @After
    public void tearDown() {
        customerFacade = null;
        customer = null;
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("my.email@com", "12345678", ClientType.COSTUMER);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void Purchase() {
        try {
            Coupon coupon = new CouponsDBDAO().getOneCoupon(1);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllPurchased() {
        try {
            TablePrinter.print(customerFacade.getCustomerCoupons());
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllPurchasedCategory() {
        try {
            TablePrinter.print(customerFacade.getCustomerCoupons(Category.ELECTRICITY));
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllPurchasedMaxPrice() {
        try {
            TablePrinter.print(customerFacade.getCustomerCoupons(20));
        } catch (CouponsSystemException e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCostumerDetails() {
        TablePrinter.print(customerFacade.getCustomerDetails());
    }
}
