package dao;

import beans.Company;
import exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CompaniesDAO {


    int getCompanyId(String email, String password);

    boolean isCompanyExists(Company company);

    boolean isCompanyEmailExists(Company company);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyID);

    List<Company> getAllCompanies();

    Company getOneCompany(int companyID);
}
