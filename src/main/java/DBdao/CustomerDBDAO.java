package DBdao;

import DB.DBmanager;
import DB.DBrunQuery;
import beans.Customer;
import dao.CustomersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class CustomerDBDAO implements CustomersDAO {


    @Override
    public boolean isCustomerEmailExists(Customer customer) {
        Map<Integer, Object> values = Map.of(1, customer.getEmail());
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COSTUMER_EMAIL_EXISTS, values);
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
    public void addCustomer(Customer customer) {
        Map<Integer, Object> values = Map.of(1, customer.getFirstName(),
                2, customer.getLastName(),
                3, customer.getEmail(),
                4, customer.getPassword());
        DBrunQuery.runQuery(DBmanager.ADD_COSTUMER, values);
    }

    @Override
    public void updateCustomer(Customer customer) {
        Map<Integer, Object> values = Map.of(1, customer.getFirstName(),
                2, customer.getLastName(),
                3, customer.getEmail(),
                4, customer.getPassword(),
                5, customer.getId());
        DBrunQuery.runQuery(DBmanager.UPDATE_COSTUMER, values);
    }

    @Override
    public void deleteCustomer(int costumerId) {
        Map<Integer, Object> map = Map.of(1, costumerId);
        DB.DBrunQuery.runQuery(DBmanager.DROP_COSTUMER, map);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_COSTUMERS);
        assert resultSet != null;
        while (true) {
            try {
                if (!resultSet.next()) break;
                customers.add(resultSetToCustomer(resultSet));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customers;
    }

    @Override
    public Customer getOneCustomer(int costumerId) {
        Map<Integer, Object> map = Map.of(1, costumerId);
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COSTUMER, map);
        assert resultSet != null;
        try {
            return resultSet.next() ? resultSetToCustomer(resultSet) : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Customer resultSetToCustomer(ResultSet resultSet) {
        try {
            return new Customer(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
