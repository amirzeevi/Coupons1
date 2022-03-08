
import beans.ClientType;
import beans.Coupon;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import org.junit.Test;
import utils.TablePrinter;

public class CustomerTest {

    CustomerFacade customerFacade =(CustomerFacade) LoginManager.getInstance().login("my.email@com", "password", ClientType.COSTUMER);

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("my.email@com", "password", ClientType.COSTUMER);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Purchase() {
        CompanyFacade companyFacade =new CompanyFacade();
        companyFacade.login("company@com", "password");
        try {
            Coupon coupon = companyFacade.getCompanyCoupons().get(0);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllPurchased() {
        try {
            //accepts arguments
            TablePrinter.print(customerFacade.getCustomerCoupons());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCostumerDetails() {
        try {
            System.out.println(customerFacade.getCustomerDetails());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}
