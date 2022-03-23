package facade;

import beans.ClientType;
import exceptions.LogInException;

/**
 * This class is a singleton meant to manage the logging in the system operation.
 */
public class LoginManager {
    private static LoginManager instance = null;

    /**
     * A private constructor so that this class can not be instantiated.
     */
    private LoginManager() {
    }

    /**
     * This method toggles the different clients that wish to log in the system, and checks their
     * login authorization for the facade. If it is correct, will return a new instance of the facade.
     * else will throw a {@link LogInException} exception with a message describing what happened.
     */
    public ClientFacade login(String email, String password, ClientType clientType) throws LogInException {
        ClientFacade facade;
        switch (clientType) {
            case ADMINISTRATOR:
                facade = new AdminFacade();
                break;
            case COMPANY:
                facade = new CompanyFacade();
                break;
            case CUSTOMER:
                facade = new CustomerFacade();
                break;
            default:
                throw new LogInException("Something went wrong");
        }
        if (facade.login(email, password)) {
            return facade;
        }
        throw new LogInException("Email or password incorrect");
    }

    /**
     * This method will return this class's instance. any second request for this class will return the same
     * reference of this object.
     */
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
