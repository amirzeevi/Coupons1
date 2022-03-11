package facade;

import beans.ClientType;
import exceptions.LogInException;


public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws LogInException {
        ClientFacade facade;
        switch (clientType) {
            case ADMINISTRATOR:
                facade = new AdminFacade();
                break;
            case COMPANY:
                facade = new CompanyFacade();
                break;
            case COSTUMER:
                facade = new CustomerFacade();
                break;
            default:
                throw new LogInException("Invalid input");
        }
        if (facade.login(email, password)) {
            return facade;
        }
        throw new LogInException("Email or password incorrect");
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }
}
