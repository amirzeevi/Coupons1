package dbdao;

import db.DBmanager;
import db.DBrunQuery;
import beans.Company;
import dao.CompaniesDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The CompanyDBDAO is the class that should access the database and update the company table.
 */
public class CompaniesDBDAO implements CompaniesDAO {
    /**
     * Retrieves from the date base the company's id, based on the specified email and password.
     * if it does not find a match, will return the id 0
     */
    @Override
    public int getCompanyId(String email, String password) {
        try {
            ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_COMPANY_ID, Map.of(1, email, 2, password));
            return resultSet.next() ? resultSet.getInt("id") : 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * This will return true if the company exist in the database based on its email, or name.
     */
    @Override
    public boolean isCompanyExist(Company company) {
        try {
            return DBrunQuery.getResultSet(
                    DBmanager.IS_COMPANY_EXIST, Map.of(1, company.getName(), 2, company.getEmail())).next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * When updating a company's name we need to make sure the name does not already exist.
     * Will return true if it finds another company with the same name.
     */
    @Override
    public void addCompany(Company company) {
        DBrunQuery.runQuery(DBmanager.ADD_COMPANY, Map.of(
                1, company.getName(),
                2, company.getEmail(),
                3, company.getPassword()));
    }

    /**
     * Adds the specified company to the companies table.
     */
    @Override
    public void updateCompany(Company company) {
        DBrunQuery.runQuery(DBmanager.UPDATE_COMPANY, Map.of(
                1, company.getEmail(),
                2, company.getPassword(),
                3, company.getId()));
    }

    /**
     * Updates the specified company to the companies table.
     */
    @Override
    public boolean UpdateCompanyEmailExist(Company company) {
        try {
            return DBrunQuery.getResultSet(
                    DBmanager.UPDATE_COMPANY_EMAIL_EXIST, Map.of(1, company.getId(), 2, company.getEmail()))
                    .next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Deletes the specified company from the companies table and also
     * will delete any coupon that the company owns
     */
    @Override
    public void deleteCompany(int companyID) {
        DBrunQuery.runQuery(DBmanager.DELETE_COMPANY, Map.of(1, companyID));
    }

    /**
     * Retrieves all companies from the database and returns a list.
     */
    @Override
    public List<Company> getAllCompanies() {
        ArrayList<Company> companiesList = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_COMPANIES);
        try {
            while (resultSet.next()) {
                companiesList.add(resultSetToCompany(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return companiesList;
    }

    /**
     * Retrieves the specified company from the database.
     */
    @Override
    public Company getOneCompany(int companyID) {
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COMPANY, Map.of(1, companyID));
        try {
            return resultSet.next() ? resultSetToCompany(resultSet) : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * A private service method to be used in multiple methods. will convert the result set into a company.
     */
    private static Company resultSetToCompany(ResultSet resultSet) throws SQLException {
        int companyID = resultSet.getInt("id");
        return new Company(
                companyID,
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                new CouponsDBDAO().getCompanyCoupons(companyID));
    }
}
