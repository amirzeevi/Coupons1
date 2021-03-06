package dbdao;

import db.DBmanager;
import db.DBrunQuery;
import beans.Customer;
import dao.CustomersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * The CustomersDBDAO is the class that should access the database and update the customers table.
 */

public class CustomersDBDAO implements CustomersDAO {
    /**
     * Returns from the database the customer's id, based on the specified email and password.
     * if it does not find a match, will return the id 0
     */
    @Override
    public int getCustomerId(String email, String password) {
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_CUSTOMER_ID, Map.of(1, email, 2, password));
        try {
            if (resultSet.next())
                return resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * When updating a customer's email we need to make sure the email does not already exist.
     * Will return true if it finds another customer with the same email.
     */
    @Override
    public boolean updateCustomerIsEmailExist(Customer customer) {
        try {
            ResultSet resultSet = DBrunQuery.getResultSet(
                    DBmanager.UPDATE_CUSTOMER_IS_EMAIL_EXIST, Map.of(
                            1, customer.getId(),
                            2, customer.getEmail()));
            resultSet.next();
            return resultSet.getInt("counter") == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * This will return true if the customer id exist in the database.
     */
    @Override
    public boolean isCustomerExist(int customerId) {
        try {
            ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_CUSTOMER_EXIST, Map.of(1, customerId));
            resultSet.next();
            return resultSet.getInt("counter") == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * This will return true if the specified email exist in the customers table.
     */
    @Override
    public boolean isCustomerEmailExists(Customer customer) {
        try {
            ResultSet resultSet = DBrunQuery.getResultSet(
                    DBmanager.IS_CUSTOMER_EMAIL_EXISTS, Map.of(1, customer.getEmail()));
            resultSet.next();
            return resultSet.getInt("counter") == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Adds the specified customer to the customers table.
     */
    @Override
    public void addCustomer(Customer customer) {
        DBrunQuery.runQuery(DBmanager.ADD_CUSTOMER, Map.of(
                1, customer.getFirstName(),
                2, customer.getLastName(),
                3, customer.getEmail(),
                4, customer.getPassword()));
    }

    /**
     * Updates the specified customer in the customers table.
     */
    @Override
    public void updateCustomer(Customer customer) {
        DBrunQuery.runQuery(DBmanager.UPDATE_CUSTOMER, Map.of(
                1, customer.getFirstName(),
                2, customer.getLastName(),
                3, customer.getEmail(),
                4, customer.getPassword(),
                5, customer.getId()));
    }

    /**
     * Deletes the specified customer from the customers table.
     */
    @Override
    public void deleteCustomer(int costumerId) {
        DBrunQuery.runQuery(DBmanager.DELETE_CUSTOMER, Map.of(1, costumerId));
    }

    /**
     * Returns a list of all customers from the database.
     */
    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_CUSTOMERS);
        try {
            while (resultSet.next()) {
                customers.add(resultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    /**
     * Returns the customer from the database based on its id.
     */
    @Override
    public Customer getOneCustomer(int costumerId) {
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_CUSTOMER, Map.of(1, costumerId));
        try {
            if (resultSet.next())
                return resultSetToCustomer(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * A private service method to be used in multiple methods that will convert the result set to a customer.
     */
    private Customer resultSetToCustomer(ResultSet resultSet) throws SQLException {
        int customerID = resultSet.getInt("id");
        return new Customer(
                customerID,
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                new CouponsDBDAO().getCostumerCoupons(customerID));
    }
}
