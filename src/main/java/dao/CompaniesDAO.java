package dao;

import beans.Company;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompaniesDAO {



    boolean isCompanyExists(Company company);

    boolean isCompanyNameExists(Company company);

    boolean isCompanyEmailExists(Company company);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyID);

    ArrayList<Company> getAllCompanies();

    Company getOneCompany(int companyID);
}
