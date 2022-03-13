package facade;

import dbdao.CompaniesDBDAO;
import dbdao.CustomersDBDAO;
import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;

import java.util.List;

public class AdminFacade extends ClientFacade {

    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin@admin") && password.equals("admin")) {
            this.companiesDAO = new CompaniesDBDAO();
            this.customerDAO = new CustomersDBDAO();
            return true;
        }
        return false;
    }

    public void addCompany(Company company) throws CouponSystemException {
        if (company == null) {
            throw new CouponSystemException("Invalid company");
        }
        if (this.companiesDAO.isCompanyExist(company)) {
            throw new CouponSystemException("Can not add company with existing name or email");
        }
        this.companiesDAO.addCompany(company);
        System.out.println("Company added");
    }

    public void deleteCompany(int companyID) throws CouponSystemException {
        // check company id
        getOneCompany(companyID);
        this.companiesDAO.deleteCompany(companyID);
        System.out.println("Company Deleted");
    }

    public void updateCompany(Company company) throws CouponSystemException {
        if (company == null) {
            throw new CouponSystemException("Invalid company");
        }
        if (this.companiesDAO.UpdateCompanyIsEmailExist(company)) {
            throw new CouponSystemException("Can not update - Email already exists");
        }
        this.companiesDAO.updateCompany(company);
        System.out.println("Company updated");
    }

    public List<Company> getAllCompanies() {
        return this.companiesDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyID) throws CouponSystemException {
        Company companyFromDB = this.companiesDAO.getOneCompany(companyID);
        if (companyFromDB == null) {
            throw new CouponSystemException("Company not found");
        }
        return companyFromDB;
    }

    public void addCustomer(Customer customer) throws CouponSystemException {
        if (customer == null) {
            throw new CouponSystemException("Invalid customer");
        }
        if (this.customerDAO.isCustomerEmailExists(customer)) {
            throw new CouponSystemException("Can not add customer with existing email");
        }
        this.customerDAO.addCustomer(customer);
        System.out.println("Customer added");
    }

    public void updateCustomer(Customer customer) throws CouponSystemException {
        if (customer == null) {
            throw new CouponSystemException("Invalid customer");
        }
        if (this.customerDAO.UpdateCustomerIsEmailExist(customer)) {
            throw new CouponSystemException("Can not update customer email already exists");
        }
        this.customerDAO.updateCustomer(customer);
        System.out.println("Customer updated");
    }

    public void deleteCustomer(int customerID) throws CouponSystemException {
        // check customer id
        getOneCustomer(customerID);
        this.customerDAO.deleteCustomer(customerID);
        System.out.println("Customer deleted");
    }

    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID) throws CouponSystemException {
        Customer customerFromDB = this.customerDAO.getOneCustomer(customerID);
        if (customerFromDB == null) {
            throw new CouponSystemException("Customer not found");
        }
        return customerFromDB;
    }
}
