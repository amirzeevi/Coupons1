package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CustomerDBDAO;
import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;
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

    public void addCompany(Company company) throws CouponSystemException {

        checkLogin();

        if (this.companiesDAO.isCompanyExists(company)) {
            throw new CouponSystemException(ErrMsg.COMPANY_SAME_EMAIL_NAME.getMsg());
        }

        this.companiesDAO.addCompany(company);
        System.out.println("Company " + company.getName() + " added");
    }


    public void deleteCompany(int companyID) throws CouponSystemException {

        checkLogin();


        Company compToDelete = getOneCompany(companyID); // check company id

        this.companiesDAO.deleteCompany(companyID);
        System.out.println("Company " + compToDelete.getName() + " Deleted");
    }


    public void updateCompany(Company company) throws CouponSystemException {
        checkLogin();


        Company companyFromDB = getOneCompany(company.getId());

        if (!companyFromDB.getEmail().equals(company.getEmail())) {
            if (this.companiesDAO.isCompanyEmailExists(company)) {
                throw new CouponSystemException(ErrMsg.COMPANY_UPDATE.getMsg() + ErrMsg.SAME_EMAIL.getMsg());
            }
        }
        this.companiesDAO.updateCompany(company);
        System.out.println("Company updated");
    }


    public List<Company> getAllCompanies() throws CouponSystemException {
        checkLogin();


        return this.companiesDAO.getAllCompanies();
    }


    public Company getOneCompany(int companyID) throws CouponSystemException {
        checkLogin();


        Company companyFromDB = this.companiesDAO.getOneCompany(companyID);

        if (companyFromDB == null) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND.getMsg());
        }
        return companyFromDB;
    }


    public void addCustomer(Customer customer) throws CouponSystemException {
        checkLogin();


        if (this.customerDAO.isCustomerEmailExists(customer)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ADD.getMsg() + ErrMsg.SAME_EMAIL.getMsg());
        }

        this.customerDAO.addCustomer(customer);
        System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " added");
    }


    public void updateCustomer(Customer customer) throws CouponSystemException {
        checkLogin();


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
        checkLogin();


        getOneCustomer(customerID); // check customer id

        this.customerDAO.deleteCustomer(customerID);
        System.out.println("Customer #" + customerID + " deleted");
    }


    public List<Customer> getAllCustomers() throws CouponSystemException {
        checkLogin();


        return this.customerDAO.getAllCustomers();
    }


    public Customer getOneCustomer(int customerID) throws CouponSystemException {
        checkLogin();


        Customer customerFromDB = this.customerDAO.getOneCustomer(customerID);

        if (customerFromDB == null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_WRONG_ID.getMsg());
        }
        return customerFromDB;
    }

    private void checkLogin() throws CouponSystemException {
        if (this.companiesDAO == null) {
            throw new CouponSystemException("You are not logged in properly");
        }
    }

}
