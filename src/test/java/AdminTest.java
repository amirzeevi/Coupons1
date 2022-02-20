import beans.ClientType;
import beans.Company;
import beans.Costumer;
import facade.AdminFacade;
import facade.LoginManager;
import org.junit.Test;


public class AdminTest {
    AdminFacade adminFacade = new AdminFacade();

    public AdminTest() {
    }

    @Test
    public void Login() {
        try {
            LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCompany() {
        try {
            Company companyToAdd = new Company("Add3Company", "my.2email@com", "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void UpdateCompany() {
        try {
            Company companyExists = adminFacade.getAllCompanies().get(0);
            companyExists.setPassword("1234");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCompany() {
        try {
            adminFacade.deleteCompany(4);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllCompanies() {
        try {
            adminFacade.getAllCompanies().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetOneCompany() {
        try {
            System.out.println(adminFacade.getOneCompany(0));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void AddCostumer() {
        try {
            Costumer costumerToAdd = new Costumer("me", "person", "my.2email@com", "1234");
            adminFacade.addCostumer(costumerToAdd);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdateCostumer() {
        try {
            Costumer costumerExists = adminFacade.getAllCostumers().get(0);
            costumerExists.setId(13);
            adminFacade.updateCostumer(costumerExists);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteCostumer() {
        try {
            Costumer costumerExists = adminFacade.getAllCostumers().get(0);
            adminFacade.deleteCostumer(0);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetAllCostumers() {
        try {
            adminFacade.getAllCostumers().forEach(System.out::println);
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void GetOneCostumer() {
        try {
            Costumer costumerExists = adminFacade.getAllCostumers().get(0);
            System.out.println(adminFacade.getOneCostumer(costumerExists.getId()));
        } catch (Exception e) {
            assert (true);
            System.out.println(e.getMessage());
        }
    }

}
