import DBdao.CouponDBDAO;
import beans.ClientType;
import beans.Coupon;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.LoginManager;
import jobs.CouponExpirationDailyJob;

import java.sql.SQLException;

public class Test {
    public static void testAll() {
        try {
            CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob(new CouponDBDAO());
//            Thread t = new Thread(couponExpirationDailyJob);
//            t.start();
//            couponExpirationDailyJob.stop();
/*
            AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
            Company c1 = new Company("myCompany", "email@com", "12345678");

            // adding new company
            adminFacade.addCompany(c1);

            Company companyWithSameName = new Company("myCompany", "different@com", "1234");
            // Can't add company with same name
            adminFacade.addCompany(companyWithSameName);

            Company companyWithSameEmail = new Company("different name", "email@com", "1234");
            // Can't add company with same email
            adminFacade.addCompany(companyWithSameEmail);

            Company companyToUpdate = adminFacade.getOneCompany(32);
            companyToUpdate.setName("not gonna happen");
            // Can't update company name
            adminFacade.updateCompany(companyToUpdate);

             companyToUpdate = adminFacade.getOneCompany(32);
            companyToUpdate.setEmail("new.email@com");
            // Update company email
            adminFacade.updateCompany(companyToUpdate);

          //  Delete company, also will delete company coupon and all coupon purchases
             companyToUpdate = adminFacade.getOneCompany(32);
            adminFacade.deleteCompany(companyToUpdate.getId());

            // Get all companies
            adminFacade.getAllCompanies().forEach(System.out::println);

            // Delete company that doesn't exist
            adminFacade.deleteCompany(31);

            // Get a company that exists in database
            System.out.println(adminFacade.getOneCompany(5));

            Costumer costumer = new Costumer("john", "sucker","email.to.share@com", "1234");
            // Add costumer
            adminFacade.addCostumer(costumer);
            Costumer costumerSameEmail = new Costumer("ori", "ginal", "email.to.share@com", "1234");
            // Can't add costumer with same email
            adminFacade.addCostumer(costumerSameEmail);
             costumer = adminFacade.getOneCostumer(7);
            costumer.setPassword("StRoNgPaSsWoRoD");
            // update costumer password
            adminFacade.updateCostumer(costumer);

            //Delete costumer that doesn't exists
            adminFacade.deleteCostumer(8);

            //Get all costumers
            adminFacade.getAllCostumers().forEach(System.out::println);

            //Get costumer doesn't exists
            adminFacade.getOneCostumer(123);

            // Get one costumer
            System.out.println(adminFacade.getOneCostumer(3));
            */

            CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("comcom@com", "4567", ClientType.COMPANY);

//            //Add new Coupon
//            Coupon coupon = new Coupon(5, 0, "myCoupon", "regular", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 20, 100, "image");
//            companyFacade.addCoupon(coupon);

//            //Can't add coupon with same title of same company
//            Coupon coupon = new Coupon(5, 1, "myCoupon", "regular", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 20, 100, "image");
//            companyFacade.addCoupon(coupon);

            // update coupon
            Coupon coupon = companyFacade.getCompanyCoupons().get(0);
            coupon.setId(25);
            companyFacade.updateCoupon(coupon);

            // delete coupon
//            companyFacade.deleteCoupon(16);

            // get all coupons
//            companyFacade.getCompanyCoupons().forEach(System.out::println);

            //
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}
