package facade;

import beans.ClientType;


public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public ClientFacade login(String email, String password, ClientType clientType) {

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
                System.out.println("Invalid input");
                return null;
        }

        if (facade.login(email, password)) {
            return facade;
        }

        System.out.println("Email or Password incorrect");
        return null;
    }


    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }
}
