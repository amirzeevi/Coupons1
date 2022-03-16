package facade;

import dbdao.CompaniesDBDAO;
import dbdao.CustomersDBDAO;
import beans.Company;
import beans.Customer;
import exceptions.CouponsSystemException;

import java.util.List;

/**
 * This class is a facade to be used by the administrator client.
 * It encapsulates all required internal connections to perform operations
 * for {@link Company} and {@link Customer} to the database.
 * In any case an operation can not be made by the administrator,
 * a {@link CouponsSystemException} exception will be thrown.
 */
public class AdminFacade extends ClientFacade {
    /**
     * This method should be called first when the administrator client wants to use this class.
     * if the email and password put in match, it will initiate the {@link dao.CompaniesDAO}
     * and {@link dao.CustomersDAO}.
     */
    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin@admin") && password.equals("admin")) {
            this.companiesDAO = new CompaniesDBDAO();
            this.customersDAO = new CustomersDBDAO();
            return true;
        }
        return false;
    }

    /**
     * This method will add the specified company to the database. If any condition does not meet with system
     * requirements it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void addCompany(Company company) throws CouponsSystemException {
        if (this.companiesDAO.isCompanyExist(company)) {
            throw new CouponsSystemException("Can not add company with existing name or email");
        }
        this.companiesDAO.addCompany(company);
        System.out.println("Company added");
    }

    /**
     * This method will delete the specified company from the database. If it does not find the company
     * it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void deleteCompany(int companyID) throws CouponsSystemException {
        // check company id
        getOneCompany(companyID);
        this.companiesDAO.deleteCompany(companyID);
        System.out.println("Company Deleted");
    }

    /**
     * This method will update the specified company to the database. If any condition does not meet with
      system requirements it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void updateCompany(Company company) throws CouponsSystemException {
        if (!companiesDAO.isCompanyExist(company)) {
            throw new CouponsSystemException("Company not found");
        }
        if (this.companiesDAO.UpdateCompanyIsEmailExist(company)) {
            throw new CouponsSystemException("Can not update - Email already exists");
        }
        this.companiesDAO.updateCompany(company);
        System.out.println("Company updated");
    }

    /**
     * This method returns a list of all companies from the database.
     */
    public List<Company> getAllCompanies(){
        return this.companiesDAO.getAllCompanies();
    }

    /**
     * This method returns the specified company from the database. if it does not find it, will throw
     * an {@link CouponsSystemException} exception.
     */
    public Company getOneCompany(int companyID) throws CouponsSystemException {
        Company companyFromDB = this.companiesDAO.getOneCompany(companyID);
        if (companyFromDB == null) {
            throw new CouponsSystemException("Company not found");
        }
        return companyFromDB;
    }

    /**
     * This method will add the specified customer to the database. If any condition does not meet with
     * system requirements it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void addCustomer(Customer customer) throws CouponsSystemException {
        if (this.customersDAO.isCustomerEmailExists(customer)) {
            throw new CouponsSystemException("Can not add customer with existing email");
        }
        this.customersDAO.addCustomer(customer);
        System.out.println("Customer added");
    }

    /**
     * This method will update the specified customer to the database. If any condition does not meet with system
     * requirements it will throw an {@link CouponsSystemException} exception with a specific message describing it.
     */
    public void updateCustomer(Customer customer) throws CouponsSystemException {
        if (this.customersDAO.getOneCustomer(customer.getId()) == null) {
            throw new CouponsSystemException("Customer not exist");
        }
        if (this.customersDAO.UpdateCustomerIsEmailExist(customer)) {
            throw new CouponsSystemException("Can not update customer email already exist");
        }
        this.customersDAO.updateCustomer(customer);
        System.out.println("Customer updated");
    }

    /**
     * This method will delete the specified customer from the database. If it does not find the customer
     * it will throw an {@link CouponsSystemException} exception.
     */
    public void deleteCustomer(int customerID) throws CouponsSystemException {
        // check customer id
        getOneCustomer(customerID);
        this.customersDAO.deleteCustomer(customerID);
        System.out.println("Customer deleted");
    }

    /**
     * This method returns a list of customers from the database.
     */
    public List<Customer> getAllCustomers() {
        return this.customersDAO.getAllCustomers();
    }

    /**
     * This method returns the specified customer from the database. if it does not find the customer, will throw
     * an {@link CouponsSystemException} exception.
     */
    public Customer getOneCustomer(int customerID) throws CouponsSystemException {
        Customer customerFromDB = this.customersDAO.getOneCustomer(customerID);
        if (customerFromDB == null) {
            throw new CouponsSystemException("Customer not found");
        }
        return customerFromDB;
    }
}
