package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CustomerDBDAO;
import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.*;


public class AdminFacade extends ClientFacade {

    public AdminFacade() {
        this.customerDAO = new CustomerDBDAO();
        this.companiesDAO = new CompaniesDBDAO();
    }


    public void addCompany(Company company) throws CouponSystemException {

        if (this.companiesDAO.isCompanyExists(company)) {
            throw new CouponSystemException(ErrMsg.COMPANY_SAME_EMAIL_NAME.getMsg());
        }

        this.companiesDAO.addCompany(company);
        System.out.println("Company " + company.getName() + " added");
    }


    public void deleteCompany(int companyID) throws CouponSystemException {

        // check company id
        Company compToDelete = getOneCompany(companyID);

        this.companiesDAO.deleteCompany(companyID);
        System.out.println("Company " + compToDelete.getName() + " Deleted");
    }


    public void updateCompany(Company company) throws CouponSystemException {

        Company companyFromDB = getOneCompany(company.getId());

        if (!companyFromDB.getEmail().equals(company.getEmail())) {
            if (this.companiesDAO.isCompanyEmailExists(company)) {
                throw new CouponSystemException(ErrMsg.COMPANY_UPDATE.getMsg() + ErrMsg.SAME_EMAIL.getMsg());
            }
        }
        this.companiesDAO.updateCompany(company);
        System.out.println("Company updated");
    }


    public ArrayList<Company> getAllCompanies() throws CouponSystemException {

        ArrayList<Company> companies = this.companiesDAO.getAllCompanies();

        if (companies.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return companies;
    }


    public Company getOneCompany(int companyID) throws CouponSystemException {

        Company companyFromDB = this.companiesDAO.getOneCompany(companyID);

        if (companyFromDB == null) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND.getMsg());
        }

        return companyFromDB;
    }


    public void addCustomer(Customer customer) throws CouponSystemException {

        if (this.customerDAO.isCustomerEmailExists(customer)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ADD.getMsg() + ErrMsg.SAME_EMAIL.getMsg());
        }

        this.customerDAO.addCustomer(customer);
        System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " added");
    }


    public void updateCustomer(Customer customer) throws CouponSystemException {

        Customer customerFromDB = getOneCustomer(customer.getId());

        if (!customerFromDB.getEmail().equals(customer.getEmail())) {
            if (this.customerDAO.isCustomerEmailExists(customer)) {
                throw new CouponSystemException(ErrMsg.CUSTOMER_UPDATE.getMsg() + ErrMsg.SAME_EMAIL.getMsg());
            }
        }
        this.customerDAO.updateCustomer(customer);
        System.out.println("Customer updated");
    }


    public void deleteCustomer(int customerID) throws CouponSystemException {

        // validate customer id
        getOneCustomer(customerID);

        this.customerDAO.deleteCustomer(customerID);
        System.out.println("Customer #" + customerID + " deleted");
    }


    public List<Customer> getAllCustomers() throws CouponSystemException {

        List<Customer> customerList = this.customerDAO.getAllCustomers();

        if (customerList.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return customerList;
    }


    public Customer getOneCustomer(int customerID) throws CouponSystemException {

        Customer customerFromDB = this.customerDAO.getOneCustomer(customerID);

        if (customerFromDB == null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_WRONG_ID.getMsg());
        }

        return customerFromDB;
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin") && password.equals("admin");
    }
}
