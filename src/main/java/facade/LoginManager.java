package facade;

import beans.ClientType;
import beans.Company;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {

        switch (clientType) {
            case ADMINISTRATOR:
                AdminFacade adminFacade = new AdminFacade();
                if (!adminFacade.login(email, password)) {
                    throw new CouponSystemException("Admin" + ErrMsg.LOGIN.getMsg());
                }
                return adminFacade;

            case COMPANY:
                CompanyFacade companyFacade = new CompanyFacade(1); // is this the correct place to enter company id?
                if (!companyFacade.login(email, password)) {
                    throw new CouponSystemException("Company" + ErrMsg.LOGIN.getMsg());
                }
                return companyFacade;

            case COSTUMER:
                CustomerFacade customerFacade = new CustomerFacade(1); // is this the correct place to enter customer id?
                if (!customerFacade.login(email, password)) {
                    throw new CouponSystemException("Customer" + ErrMsg.LOGIN.getMsg());
                }
                return customerFacade;

            default:
                System.out.println("Invalid input");
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
