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
 * The CustomerDBDAO is the class that should access the database and update the company table.
 */

public class CustomerDBDAO implements CustomersDAO {
    /**
     * Retrieves from the date base the custmer's id, based on the specified email and password.
     * if it does not find a match, will return the id 0
     */
    @Override
    public int getCustomerId(String email, String password) {
        ResultSet resultSet = DBrunQuery.getResultSet
                (DBmanager.GET_CUSTOMER_ID, Map.of(1, email, 2, password));
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
    public boolean UpdateCustomerEmailExist(Customer customer) {
        try {
            return DBrunQuery.getResultSet
                    (DBmanager.UPDATE_CUSTOMER_EMAIL_EXIST, Map.of(1, customer.getId(), 2, customer.getEmail()))
                    .next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    /**
     * This will return true if the specified email exist in the database.
     */
    @Override
    public boolean isCustomerEmailExists(Customer customer) {
        try {
            return DBrunQuery.getResultSet(DBmanager.IS_CUSTOMER_EMAIL_EXISTS, Map.of(1, customer.getEmail())).next();
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
     * Updates the specified customer to the customers table.
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
     * Retrieves all customers from the database and returns a list.
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
     * Retrieves the specified customer from the database based on its id.
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
     *A private service method to be used in multiple method that will convert the result set into a customer.
     */
    private static Customer resultSetToCustomer(ResultSet resultSet) throws SQLException {
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
