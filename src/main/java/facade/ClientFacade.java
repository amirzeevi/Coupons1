package facade;

import dao.CompaniesDAO;
import dao.CustomersDAO;
import dao.CouponsDAO;

/**
 * This abstract class is the super class for all client facades. It holds all the DAO classes
 * to be used by different facades.
 */
public abstract class ClientFacade {

    protected CouponsDAO couponsDAO;
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customerDAO;

    /**
     * An abstract login method to be overridden by the facades. for the company and customer facade
     * it matches the specified email and password to the database and returns their id if it finds.
     * also initiates the DAO classes.
     */
    public abstract boolean login(String email, String password);
}
