
import beans.ClientType;
import beans.Coupon;
import facade.CompanyFacade;
import facade.CostumerFacade;
import facade.LoginManager;
import org.junit.Test;

public class CustomerTest {
    CostumerFacade costumerFacade = new CostumerFacade(12);

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("my.email@com", "1234", ClientType.COSTUMER);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Purchase() {
        CompanyFacade companyFacade = new CompanyFacade(41);
        try {
            Coupon coupon = companyFacade.getCompanyCoupons().get(0);
            costumerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllPurchased() {
        try {
            costumerFacade.getCostumerCoupons().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetCostumerDetails() {
        try {
            System.out.println(costumerFacade.getCostumerDetails());
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }
}
