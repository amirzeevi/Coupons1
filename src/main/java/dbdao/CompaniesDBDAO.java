package dbdao;

import db.DBmanager;
import db.DBrunQuery;
import beans.Company;
import beans.Coupon;
import dao.CompaniesDAO;
import dao.CouponsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {


    @Override
    public int getCompanyId(String email, String password) {
        Map<Integer, Object> values = Map.of(1, email, 2, password);
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_COMPANY_ID, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            return 0;
        }
    }


    @Override
    public boolean isCompanyExists(Company company) {
        Map<Integer, Object> values = Map.of(1, company.getName(), 2, company.getEmail());
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COMPANY_EXISTS, values);
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
            return resultSet.getInt("counter") == 1;
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
    public List<Company> getAllCompanies() {
        ArrayList<Company> companyList = new ArrayList<>();
        try {
            ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_COMPANIES);

            assert resultSet != null;
            while (resultSet.next()) {
                companyList.add(resultSetToCompany(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return companyList;
    }


    @Override
    public Company getOneCompany(int companyID) {
        Map<Integer, Object> map = Map.of(1, companyID);
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COMPANY, map);

        assert resultSet != null;
        try {
            resultSet.next();
            return resultSetToCompany(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }


    private static Company resultSetToCompany(ResultSet resultSet) throws SQLException {
        CouponsDAO couponsDAO = new CouponDBDAO();
        List<Coupon> companyCoupon = (couponsDAO.getCompanyCoupons(resultSet.getInt(("id"))));
        return new Company(resultSet.getInt(("id")),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                companyCoupon);
    }
}
