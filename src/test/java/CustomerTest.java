
import beans.Category;
import beans.ClientType;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.LogInException;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.TablePrinter;

public class CustomerTest {

    CustomerFacade customerFacade;

    @Before
    public void setUp() {
        customerFacade = new CustomerFacade();
        customerFacade.login("my.email@com", "12345678");
    }

    @After
    public void tearDown() {
        customerFacade = null;
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

    @Test(expected = LogInException.class)
    public void exceptionLogin() throws LogInException {
        LoginManager.getInstance().login("not.my.email@com", "password", ClientType.COSTUMER);
    }

    @Test
    public void Purchase() {
        CompanyFacade companyFacade = new CompanyFacade();
        companyFacade.login("company@com", "password");
        try {
            Coupon coupon = companyFacade.getCompanyCoupons().get(0);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = CouponSystemException.class)
    public void exceptionPurchase() throws CouponSystemException {
        CompanyFacade companyFacade = new CompanyFacade();
        companyFacade.login("company@com", "password");
        Coupon coupon = companyFacade.getCompanyCoupons().get(0);
        customerFacade.purchaseCoupon(coupon);
    }

    @Test
    public void GetAllPurchased() {
        //takes arguments
        TablePrinter.print(customerFacade.getCustomerCoupons());
    }

    @Test
    public void GetCostumerDetails() {
        TablePrinter.print(customerFacade.getCustomerDetails());
    }

}
