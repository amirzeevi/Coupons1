import dbdao.CompaniesDBDAO;

public class Program {

    public static void main(String[] args) {

        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        companiesDBDAO.getAllCompanies().forEach(System.out::println);
    }
}



