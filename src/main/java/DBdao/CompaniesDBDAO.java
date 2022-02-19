package DBdao;

import DB.DBmanager;
import DB.DBrunQuery;
import beans.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CompaniesDBDAO implements dao.CompaniesDAO {


    @Override
    public boolean isCompanyExists(Company company) {

        Map<Integer, Object> values = Map.of(1, company.getName(), 2, company.getEmail());
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COMPANY_EXISTS, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean isCompanyNameExists(Company company) {

        Map<Integer, Object> values = Map.of(1, company.getName());
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COMPANY_NAME_EXISTS, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean isCompanyEmailExists(Company company) {

        Map<Integer, Object> values = Map.of(1, company.getEmail());
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public void addCompany(Company company) {

        Map<Integer, Object> map = Map.of(1, company.getName(), 2, company.getEmail(), 3, company.getPassword());
        DBrunQuery.runQuery(DBmanager.ADD_COMPANY, map);
    }


    @Override
    public void updateCompany(Company company) {

        Map<Integer, Object> map = Map.of(1, company.getEmail(), 2, company.getPassword(), 3, company.getId());
        DBrunQuery.runQuery(DBmanager.UPDATE_COMPANY, map);
    }


    @Override
    public void deleteCompany(int companyID) {

        Map<Integer, Object> map = Map.of(1, companyID);
        DBrunQuery.runQuery(DBmanager.DROP_COMPANY, map);
    }


    @Override
    public ArrayList<Company> getAllCompanies() {

        ArrayList<Company> companyList = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_COMPANIES);

        assert resultSet != null;
        while (true) {
            try {
                if (!resultSet.next()) break;
                companyList.add(resultSetToCompany(resultSet));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return companyList;
    }


    @Override
    public Company getOneCompany(int companyID) {

        Map<Integer, Object> map = Map.of(1, companyID);
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COMPANY, map);

        assert resultSet != null;
        try {
            return resultSet.next() ? resultSetToCompany(resultSet) : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    private static Company resultSetToCompany(ResultSet resultSet) {

        Company company = new Company();
        try {
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
            company.setEmail(resultSet.getString("email"));
            company.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return company;
    }
}
