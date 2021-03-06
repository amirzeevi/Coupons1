package dao;

import beans.Company;

import java.util.List;

public interface CompaniesDAO {

    int getCompanyId(String email, String password);

    boolean isCompanyExist(int companyId);
  
    boolean isCompanyNameOrEmailExist(Company company);

    boolean updateCompanyIsEmailExist(Company company);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyID);

    List<Company> getAllCompanies();
    
    Company getOneCompany(int companyID);
}
