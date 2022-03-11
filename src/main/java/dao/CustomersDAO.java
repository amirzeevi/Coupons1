package dao;

import beans.Customer;

import java.util.List;
/**
 * The CustomerDAO interface is to be implemented by the class
 * that should access the database and update the company table.
 */
public interface CustomersDAO {
    /**
     * Retrieves from the date base the custmer's id, based on the specified email and password.
     * if it does not find a match, will return the id 0
     */
    int getCustomerId(String email, String password);
    /**
     * When updating a customer's email we need to make sure the email does not already exist.
     * Will return true if it finds another customer with the same email.
     */
    boolean canNotUpdateCustomer(Customer customer);
    /**
     * This will return true if the specified email exist in the database.
     */
    boolean isCustomerEmailExists(Customer customer);
    /**
     * Adds the specified customer to the customers table.
     */
    void addCustomer(Customer customer);
    /**
     * Updates the specified customer to the customers table.
     */
    void updateCustomer(Customer customer);
    /**
     * Deletes the specified customer from the customers table.
     */
    void deleteCustomer(int costumerId);
    /**
     * Retrieves all customers from the database and returns a list.
     */
    List<Customer> getAllCustomers();
    /**
     * Retrieves the specified customer from the database based on its id.
     */
    Customer getOneCustomer(int costumerId);

}
