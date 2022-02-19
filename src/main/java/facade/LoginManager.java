package facade;

import beans.ClientType;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException{

        switch (clientType) {
            case ADMINISTRATOR:
                AdminFacade adminFacade = new AdminFacade();
                if (!adminFacade.login(email, password)) {
                    throw new CouponSystemException("ADMIN" + ErrMsg.LOGIN.getMsg());
                }
                return adminFacade;
            case COMPANY:
                CompanyFacade companyFacade = new CompanyFacade(39);
                if ((!companyFacade.login(email, password))) {
                    throw new CouponSystemException("COMPANY" + ErrMsg.LOGIN.getMsg());
                }
                return companyFacade;
            case COSTUMER:
                CostumerFacade costumerFacade = new CostumerFacade(9);
                if (!costumerFacade.login(email, password)) {
                    throw new CouponSystemException("COSTUMER" + ErrMsg.LOGIN.getMsg());
                }
                return costumerFacade;
            default:
                System.out.println("INVALID INPUT");
                return null;
        }
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }
}
