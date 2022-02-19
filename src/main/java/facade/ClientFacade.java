package facade;

import dao.CompaniesDAO;
import dao.CostumersDAO;
import dao.CouponsDAO;
import exceptions.CouponSystemException;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CouponsDAO couponDAO;
    protected CompaniesDAO companiesDAO;
    protected CostumersDAO costumerDAO;

    public abstract boolean login(String email, String password) throws SQLException, CouponSystemException, InterruptedException;
}
