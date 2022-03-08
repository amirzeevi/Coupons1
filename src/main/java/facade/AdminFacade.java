package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CustomerDBDAO;
import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import exceptions.LogInException;

import java.util.List;


public class AdminFacade extends ClientFacade {

    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin@admin") && password.equals("admin")) {
            this.companiesDAO = new CompaniesDBDAO();
            this.customerDAO = new CustomerDBDAO();
            return true;
        }
        return false;
    }

    public void addCompany(Company company) throws CouponSystemException, LogInException {

        checkLogin();

        if (this.companiesDAO.isCompanyExists(company)) {
            throw new CouponSystemException("Can not add company with existing name or email");
        }

        this.companiesDAO.addCompany(company);
        System.out.println("Company added");
    }


    public void deleteCompany(int companyID) throws CouponSystemException, LogInException {

        checkLogin();

        // check company id
        Company compToDelete = getOneCompany(companyID);

        this.companiesDAO.deleteCompany(companyID);
        System.out.println("Company Deleted");
    }


    public void updateCompany(Company company) throws CouponSystemException, LogInException {

        checkLogin();

        Company companyFromDB = getOneCompany(company.getId());

        if (!companyFromDB.getEmail().equals(company.getEmail())) {
            if (this.companiesDAO.isCompanyEmailExists(company)) {
                throw new CouponSystemException("Can not update company to existing company name");
            }
        }
        this.companiesDAO.updateCompany(company);
        System.out.println("Company updated");
    }


    public List<Company> getAllCompanies() throws LogInException {
        checkLogin();
        return this.companiesDAO.getAllCompanies();
    }


    public Company getOneCompany(int companyID) throws CouponSystemException, LogInException {

        checkLogin();

        Company companyFromDB = this.companiesDAO.getOneCompany(companyID);

        if (companyFromDB == null) {
            throw new CouponSystemException("Company not found");
        }
        return companyFromDB;
    }


    public void addCustomer(Customer customer) throws CouponSystemException, LogInException {

        checkLogin();

        if (this.customerDAO.isCustomerEmailExists(customer)) {
            throw new CouponSystemException("Can not add customer with existing email");
        }

        this.customerDAO.addCustomer(customer);
        System.out.println("Customer added");
    }


    public void updateCustomer(Customer customer) throws CouponSystemException, LogInException {

        checkLogin();

        Customer customerFromDB = getOneCustomer(customer.getId());

        if (!customerFromDB.getEmail().equals(customer.getEmail())) {
            if (this.customerDAO.isCustomerEmailExists(customer)) {
                throw new CouponSystemException("Can not update customer email already exists");
            }
        }
        this.customerDAO.updateCustomer(customer);
        System.out.println("Customer updated");
    }


    public void deleteCustomer(int customerID) throws CouponSystemException, LogInException {

        checkLogin();

        // check customer id
        getOneCustomer(customerID);

        this.customerDAO.deleteCustomer(customerID);
        System.out.println("Customer deleted");
    }


    public List<Customer> getAllCustomers() throws LogInException {
        checkLogin();
        return this.customerDAO.getAllCustomers();
    }


    public Customer getOneCustomer(int customerID) throws CouponSystemException, LogInException {

        checkLogin();

        Customer customerFromDB = this.customerDAO.getOneCustomer(customerID);

        if (customerFromDB == null) {
            throw new CouponSystemException("Customer not found");
        }
        return customerFromDB;
    }

    private void checkLogin() throws LogInException {
        if (this.companiesDAO == null) {
            throw new LogInException("You are not logged in");
        }
    }

}
