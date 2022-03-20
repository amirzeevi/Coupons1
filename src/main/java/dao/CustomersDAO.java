package dao;

import beans.Customer;

import java.util.List;

/**
 * The CustomersDAO interface is to be implemented by the class
 * that should access the database and update the customers table.
 */
public interface CustomersDAO {
    /**
     * Returns from the database the customer's id, based on the specified email and password.
     * if it does not find a match, will return the id 0
     */
    int getCustomerId(String email, String password);

    /**
     * When updating a customer's email we need to make sure the email does not already exist.
     * Will return true if it finds another customer with the same email.
     */
    boolean UpdateCustomerIsEmailExist(Customer customer);

    /**
     * This will return true if the customer id exist in the database.
     */
    boolean isCustomerExist(int customerId);

    /**
     * This will return true if the specified email exist in the customers table.
     */
    boolean isCustomerEmailExists(Customer customer);

    /**
     * Adds the specified customer to the customers table.
     */
    void addCustomer(Customer customer);

    /**
     * Updates the specified customer in the customers table.
     */
    void updateCustomer(Customer customer);

    /**
     * Deletes the specified customer from the customers table.
     */
    void deleteCustomer(int costumerId);

    /**
     * Returns a list of all customers from the database.
     */
    List<Customer> getAllCustomers();

    /**
     * Returns the customer from the database based on its id.
     */
    Customer getOneCustomer(int costumerId);

}
