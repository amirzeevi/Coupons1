package dao;

import beans.Customer;

import java.util.List;

public interface CustomersDAO {

    int getCustomerId(String email, String password);

    boolean isCustomerEmailExists(Customer customer);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int costumerId);

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int costumerId);

}
