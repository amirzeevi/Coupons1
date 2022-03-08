package facade;

import dao.CompaniesDAO;
import dao.CustomersDAO;
import dao.CouponsDAO;



public abstract class ClientFacade {

    protected CouponsDAO couponDAO;
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customerDAO;


    public abstract boolean login(String email, String password);
}
