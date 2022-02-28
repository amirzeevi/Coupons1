
import beans.ClientType;
import beans.Coupon;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import org.junit.Test;
import utils.TablePrinter;

public class CustomerTest {

    CustomerFacade customerFacade = new CustomerFacade(16);

    @Test
    public void Login() {
        try {
            // need to also change customer id in Login Manager
            LoginManager.getInstance().login("my.email@com", "1234", ClientType.COSTUMER);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Purchase() {
        CompanyFacade companyFacade = new CompanyFacade(7);
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
            TablePrinter.print(customerFacade.getCostumerCoupons());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCostumerDetails() {
        try {
            System.out.println(customerFacade.getCostumerDetails());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}
