package dao;

import beans.Company;

import java.util.List;

/**
 * The CompanyDAO interface is to be implemented by the class
 * that should access the database and update the company table.
 */
public interface CompaniesDAO {
    /**
     * Returns from the database the company's id, based on the specified email and password.
     * if it does not find a match, will return the id 0
     */
    int getCompanyId(String email, String password);

    /**
     * This will return true if the company exist in the database based on its email, or name.
     */
    boolean isCompanyExist(Company company);

    /**
     * When updating a company's name we need to make sure the name does not already exist.
     * Will return true if it finds another company with the same name.
     */
    boolean UpdateCompanyIsEmailExist(Company company);

    /**
     * Adds the specified company to the companies table.
     */
    void addCompany(Company company);

    /**
     * Updates the specified company in the companies table.
     */
    void updateCompany(Company company);

    /**
     * Deletes the specified company from the companies table, also
     * will delete any coupon that the company owns
     */
    void deleteCompany(int companyID);

    /**
     * Returns a list of all companies from the database.
     */
    List<Company> getAllCompanies();

    /**
     * Returns the specified company from the database.
     */
    Company getOneCompany(int companyID);
}
