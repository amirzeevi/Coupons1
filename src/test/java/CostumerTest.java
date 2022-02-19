
import beans.ClientType;
import beans.Coupon;
import facade.CompanyFacade;
import facade.CostumerFacade;
import facade.LoginManager;
import junit.framework.TestCase;

public class CostumerTest extends TestCase {
    CostumerFacade costumerFacade = new CostumerFacade(12);

    public void testLogin() {
        try {
            LoginManager.getInstance().login("my.email@com", "1234", ClientType.COSTUMER);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testPurchase() {
        CompanyFacade companyFacade = new CompanyFacade(41);
        try {
            Coupon coupon = companyFacade.getCompanyCoupons().get(0);
            costumerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetAllPurchased() {
        try {
            costumerFacade.getCostumerCoupons().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetCostumerDetails() {
        try {
            System.out.println(costumerFacade.getCostumerDetails());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}
