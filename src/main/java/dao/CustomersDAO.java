package dao;

import beans.Customer;

import java.util.ArrayList;

public interface CustomersDAO {

    boolean isCustomerEmailExists(Customer customer);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int costumerId);

    ArrayList<Customer> getAllCustomers();

    Customer getOneCustomer(int costumerId);

}
