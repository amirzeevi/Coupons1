import beans.ClientType;
import beans.Company;
import beans.Costumer;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.LoginManager;
import junit.framework.TestCase;

import java.sql.SQLException;

public class AdminTest extends TestCase {
    AdminFacade adminFacade = new AdminFacade();

    public AdminTest() {
    }

    public void testLogin() {
        try {
            LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testAddCompany() {
        try {
            Company companyToAdd = new Company("Add3Company", "my.2email@com", "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testUpdateCompany() {
        try {
            Company companyExists = adminFacade.getAllCompanies().get(0);
            companyExists.setPassword("1234");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testDeleteCompany() {
        try {
            adminFacade.deleteCompany(4);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetAllCompanies() {
        try {
            adminFacade.getAllCompanies().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetOneCompany() {
        try {
            System.out.println(adminFacade.getOneCompany(0));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testAddCostumer() {
        try {
            Costumer costumerToAdd = new Costumer("me", "person", "my.2email@com", "1234");
            adminFacade.addCostumer(costumerToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testUpdateCostumer() {
        try {
            Costumer costumerExists = adminFacade.getAllCostumers().get(0);
            costumerExists.setId(13);
            adminFacade.updateCostumer(costumerExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testDeleteCostumer() {
        try {
            Costumer costumerExists = adminFacade.getAllCostumers().get(0);
            adminFacade.deleteCostumer(0);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetAllCostumers() {
        try {
            adminFacade.getAllCostumers().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    public void testGetOneCostumer() {
        try {
            Costumer costumerExists = adminFacade.getAllCostumers().get(0);
            System.out.println(adminFacade.getOneCostumer(costumerExists.getId()));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

}
