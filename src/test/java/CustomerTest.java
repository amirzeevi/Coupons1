import beans.Category;
import beans.ClientType;
import beans.Coupon;
import dbdao.CouponDBDAO;
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
        customerFacade.login("my.email@com", "1234");
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


    @Test
    public void Purchase() {
        try {
            Coupon coupon = new CouponDBDAO().getOneCoupon(3);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllPurchased() {
        TablePrinter.print(customerFacade.getCustomerCoupons());
    }

    @Test
    public void GetAllPurchasedCategory() {
        TablePrinter.print(customerFacade.getCustomerCoupons(Category.ELECTRICITY));
    }

    @Test
    public void GetAllPurchasedMaxPrice() {
        TablePrinter.print(customerFacade.getCustomerCoupons(20));
    }

    @Test
    public void GetCostumerDetails() {
        TablePrinter.print(customerFacade.getCustomerDetails());
    }
}
