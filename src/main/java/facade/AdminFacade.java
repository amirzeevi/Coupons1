package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CustomerDBDAO;
import DBdao.CouponDBDAO;
import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.*;


public class AdminFacade extends ClientFacade {

    public AdminFacade() {
        this.customerDAO = new CustomerDBDAO();
        this.companiesDAO = new CompaniesDBDAO();
        this.couponDAO = new CouponDBDAO();
    }


    public void addCompany(Company company) throws CouponSystemException {

        if (this.companiesDAO.isCompanyExists(company)) {
            throw new CouponSystemException(ErrMsg.COMPANY_SAME_EMAIL_NAME.getMsg());
        }

        this.companiesDAO.addCompany(company);
        System.out.println("COMPANY " + company.getName() + " ADDED TO DATABASE");
    }


    public void deleteCompany(int companyID) throws CouponSystemException {

        // will validate company id
        Company compToDelete = getOneCompany(companyID);

        this.companiesDAO.deleteCompany(companyID);
        System.out.println("COMPANY " + compToDelete.getName() + " DELETED FROM DATABASE");
    }


    public void updateCompany(Company company) throws CouponSystemException {

        Company companyFromDB = getOneCompany(company.getId());

        if (!companyFromDB.getEmail().equals(company.getEmail())) {
            if (this.companiesDAO.isCompanyEmailExists(company)) {
                throw new CouponSystemException(ErrMsg.COMPANY_UPDATE.getMsg() + ErrMsg.COMPANY_SAME_EMAIL);
            }
        }
        this.companiesDAO.updateCompany(company);
        System.out.println("COMPANY UPDATED");
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


    public void addCostumer(Customer customer) throws CouponSystemException {

        if (this.customerDAO.isCustomerEmailExists(customer)) {
            throw new CouponSystemException(ErrMsg.COSTUMER_ADD.getMsg() + ErrMsg.COSTUMER_SAME_EMAIL.getMsg());
        }

        this.customerDAO.addCustomer(customer);
        System.out.println("COSTUMER " + customer.getFirstName() + " " + customer.getLastName() + " ADDED TO DATABASE");
    }


    public void updateCostumer(Customer customer) throws CouponSystemException {

        Customer customerFromDB = getOneCostumer(customer.getId());

        if (!customerFromDB.getEmail().equals(customer.getEmail())) {
            if (this.customerDAO.isCustomerEmailExists(customer)) {
                throw new CouponSystemException(ErrMsg.COSTUMER_UPDATE.getMsg() + ErrMsg.COSTUMER_SAME_EMAIL.getMsg());
            }
        }
        this.customerDAO.updateCustomer(customer);
        System.out.println("COSTUMER UPDATED");
    }


    public void deleteCustomer(int customerID) throws CouponSystemException {

        // validates customer id
        getOneCostumer(customerID);

        this.customerDAO.deleteCustomer(customerID);
        System.out.println("COSTUMER #" + customerID + " DELETED FROM DATABASE");
    }


    public List<Customer> getAllCustomers() throws CouponSystemException {

        List<Customer> customerList = this.customerDAO.getAllCustomers();

        if (customerList.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return customerList;
    }


    public Customer getOneCostumer(int costumerID) throws CouponSystemException {

        Customer customerFromDB = this.customerDAO.getOneCustomer(costumerID);

        if (customerFromDB == null) {
            throw new CouponSystemException(ErrMsg.COSTUMER_WRONG_ID.getMsg());
        }

        return customerFromDB;
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin") && password.equals("admin");
    }
}
