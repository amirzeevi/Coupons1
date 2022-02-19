package facade;

import DBdao.CompaniesDBDAO;
import DBdao.CostumerDBDAO;
import DBdao.CouponDBDAO;
import beans.Company;
import beans.Costumer;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;
import java.util.*;


public class AdminFacade extends ClientFacade {

    public AdminFacade() {
        this.costumerDAO = new CostumerDBDAO();
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

        Company compToDelete = getOneCompany(companyID);

        this.companiesDAO.deleteCompany(companyID);
        System.out.println("COMPANY " + compToDelete.getName() + " DELETED FROM DATABASE");
    }


    public void updateCompany(Company company) throws CouponSystemException{

        Company companyFromDB = getOneCompany(company.getId());

        if (!(companyFromDB.getName().equals(company.getName()))) {
            throw new CouponSystemException(ErrMsg.COMPANY_UPDATE_NAME.getMsg());
        }

        if (!(companyFromDB.getEmail().equals(company.getEmail()))) {
            if (this.companiesDAO.isCompanyEmailExists(company)) {
                throw new CouponSystemException(ErrMsg.COMPANY_UPDATE.getMsg() + ErrMsg.COMPANY_SAME_EMAIL.getMsg());
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


    public void addCostumer(Costumer costumer) throws CouponSystemException {

        if (this.costumerDAO.isCostumerEmailExists(costumer)) {
            throw new CouponSystemException(ErrMsg.COSTUMER_ADD.getMsg() + ErrMsg.COSTUMER_SAME_EMAIL.getMsg());
        }

        this.costumerDAO.addCostumer(costumer);
        System.out.println("COSTUMER " + costumer.getFirstName() + " " + costumer.getLastName() + " ADDED TO DATABASE");
    }


    public void updateCostumer(Costumer costumer) throws CouponSystemException {

        Costumer costumerFromDB = getOneCostumer(costumer.getId());

        if (!(costumerFromDB.getEmail().equals(costumer.getEmail()))) {
            if (this.costumerDAO.isCostumerEmailExists(costumer)) {
                throw new CouponSystemException(ErrMsg.COSTUMER_UPDATE.getMsg() + ErrMsg.COSTUMER_SAME_EMAIL.getMsg());
            }
        }

        this.costumerDAO.updateCostumer(costumer);
        System.out.println("COSTUMER UPDATED");
    }


    public void deleteCostumer(int costumerID) throws CouponSystemException {

        getOneCostumer(costumerID);
        this.costumerDAO.deleteCostumer(costumerID);
        System.out.println("COSTUMER #" + costumerID + " DELETED FROM DATABASE");
    }


    public List<Costumer> getAllCostumers() throws CouponSystemException {

        List<Costumer> costumerList = this.costumerDAO.getAllCostumers();

        if (costumerList.isEmpty()) {
            throw new CouponSystemException(ErrMsg.LIST.getMsg());
        }

        return costumerList;
    }


    public Costumer getOneCostumer(int costumerID) throws CouponSystemException {

        Costumer costumerFromDB = this.costumerDAO.getOneCostumer(costumerID);

        if (costumerFromDB == null) {
            throw new CouponSystemException(ErrMsg.COSTUMER_WRONG_ID.getMsg());
        }

        return costumerFromDB;
    }


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin") && password.equals("admin");
    }
}
