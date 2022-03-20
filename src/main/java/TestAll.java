import beans.*;
import db.DBmanager;
import db.DBrunQuery;
import dbdao.CategoriesDBDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import exceptions.CouponsSystemException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.LoginManager;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class TestAll {
    public static void testAll() {
        System.out.println("*********************************************************************");
        System.out.println("                           CREATE TABLES TEST");
        System.out.println("*********************************************************************");

        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.SET_TIME_ZONE);
        DBrunQuery.runQuery(DBmanager.CREATE_TRIGGER_COUPON_PURCHASE);
        System.out.println("CREATED");
        System.out.println("*********************************************************************");
        System.out.println("                         ADMIN FACADE TEST");
        System.out.println("*********************************************************************");

        AdminFacade adminFacade = new AdminFacade();
        adminFacade.login("admin@admin", "admin");


        System.out.println("TESTING LOGIN");
        try {
            LoginManager.getInstance().login("admin@admin", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING LOGIN FAIL");
        try {
            LoginManager.getInstance().login("Xadmin@admin", "admin", ClientType.ADMINISTRATOR);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING ADD COMPANY");
        try {
            Company companyToAdd = new Company(
                    "Company",
                    "company@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING ADDING SECOND COMPANY");
        try {
            Company companyToAdd = new Company(
                    "Company2",
                    "company2@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING ADD COMPANY NAME FAIL");
        try {
            Company companyToAdd = new Company(
                    "Company",
                    "company3@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING ADD COMPANY EMAIL FAIL");

        try {
            Company companyToAdd = new Company(
                    "Company3",
                    "company2@com",
                    "1234");
            adminFacade.addCompany(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING UPDATE COMPANY");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setEmail("New@com");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING UPDATE COMPANY NAME FAIL");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setName("john");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING UPDATE COMPANY EMAIL FAIL");
        try {
            Company companyExists = adminFacade.getOneCompany(1);
            companyExists.setEmail("company2@com");
            adminFacade.updateCompany(companyExists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING DELETE COMPANY");
        try {
            adminFacade.deleteCompany(2);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING DELETE COMPANY FAIL");
        try {
            adminFacade.deleteCompany(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING GET ALL COMPANIES");
        TablePrinter.print(adminFacade.getAllCompanies());
        System.out.println();


        System.out.println("TESTING GET ONE COMPANY");
        try {
            TablePrinter.print(adminFacade.getOneCompany(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING GET ONE COMPANY FAIL");
        try {
            TablePrinter.print(adminFacade.getOneCompany(1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING ADD CUSTOMER");
        try {
            Customer customerToAdd = new Customer(
                    "Ploni",
                    "Israeli",
                    "my.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING ADD SECOND CUSTOMER");
        try {
            Customer customerToAdd = new Customer(
                    "Almoni",
                    "Israeli",
                    "my.email2@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING ADD CUSTOMER EMAIL FAIL");
        try {
            Customer customerToAdd = new Customer(
                    "Gilad",
                    "Israeli",
                    "my.email@com",
                    "1234");
            adminFacade.addCustomer(customerToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING UPDATE CUSTOMER");
        try {
            Customer customerExists = adminFacade.getOneCustomer(1);
            customerExists.setPassword("12345678");
            adminFacade.updateCustomer(customerExists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING UPDATE CUSTOMER EMAIL FAIL");
        try {
            Customer customerExists = adminFacade.getOneCustomer(1);
            customerExists.setEmail("my.email2@com");
            adminFacade.updateCustomer(customerExists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING DELETE CUSTOMER");
        try {
            adminFacade.deleteCustomer(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING DELETE CUSTOMER FAIL");
        try {
            adminFacade.deleteCustomer(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING GET ALL CUSTOMERS");
        TablePrinter.print(adminFacade.getAllCustomers());


        System.out.println("TESTING GET ONE CUSTOMER");
        try {
            TablePrinter.print(adminFacade.getOneCustomer(1));
        } catch (CouponsSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING GET ONE CUSTOMER FAIL");
        try {
            TablePrinter.print(adminFacade.getOneCustomer(1000));

        } catch (CouponsSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("*********************************************************************");
        System.out.println("                          COMPANY FACADE TEST");
        System.out.println("*********************************************************************");

        Company company;
        CompanyFacade companyFacade;
        company = new CompaniesDBDAO().getAllCompanies().get(0);
        companyFacade = new CompanyFacade();
        companyFacade.login(company.getEmail(), company.getPassword());
        new CategoriesDBDAO().addAllCategories();

        System.out.println("TESTING LOGIN");
        try {
            LoginManager.getInstance().login(company.getEmail(), company.getPassword(), ClientType.COMPANY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING LOGIN FAIL");
        try {
            LoginManager.getInstance().login("company2@com", "Xpassword", ClientType.COMPANY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TEST ADD COUPON");
        try {
            Coupon couponToAdd = new Coupon(
                    company.getId(),
                    Category.ELECTRICITY,
                    "Electric Bike",
                    "myDescription",
                    Date.valueOf(LocalDate.now()),
                    Date.valueOf(LocalDate.now().plusDays(12)),
                    20,
                    99.99,
                    "image");

            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING ADD SECOND COUPON");
        try {
            Coupon couponToAdd = new Coupon(
                    company.getId(),
                    Category.VACATION,
                    "Hotel California",
                    "Vacation",
                    Date.valueOf(LocalDate.now()),
                    Date.valueOf(LocalDate.now().plusDays(12)),
                    20,
                    199.99,
                    "image");

            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING ADD COUPON FAIL");
        try {
            Coupon couponToAdd = new Coupon(
                    company.getId(),
                    Category.VACATION,
                    "Hotel California",
                    "Vacation",
                    Date.valueOf(LocalDate.now()),
                    Date.valueOf(LocalDate.now().plusDays(12)),
                    20,
                    199.99,
                    "image");
            companyFacade.addCoupon(couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING UPDATE COUPON");
        try {
            Coupon existsCoupon = companyFacade.getCompanyCoupons().get(1);
            existsCoupon.setPrice(120.0);
            companyFacade.updateCoupon(existsCoupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING UPDATE COUPON FAIL");
        try {
            Coupon existsCoupon = companyFacade.getCompanyCoupons().get(1);
            existsCoupon.setEndDate(Date.valueOf(LocalDate.now().minusDays(20)));
            companyFacade.updateCoupon(existsCoupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING DELETE COUPON");
        try {
            companyFacade.deleteCoupon(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING DELETE COUPON FAIL");
        try {
            companyFacade.deleteCoupon(17);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING GET COMPANY COUPONS");
        TablePrinter.print(companyFacade.getCompanyCoupons());
        System.out.println();

        System.out.println("TESTING GET COMPANY COUPONS BY CATEGORY");
        TablePrinter.print(companyFacade.getCompanyCoupons(Category.ELECTRICITY));
        System.out.println();

        System.out.println("TESTING GET COMPANY COUPONS BY MAX PRICE");
        TablePrinter.print(companyFacade.getCompanyCoupons(100));
        System.out.println();

        System.out.println("TESTING GET COMPANY DETAILS");
        TablePrinter.print(companyFacade.getCompanyDetails());

        System.out.println("*********************************************************************");
        System.out.println("                          CUSTOMER FACADE TEST");
        System.out.println("*********************************************************************");

        CustomerFacade customerFacade;
        Customer customer;
        customer = new CustomersDBDAO().getAllCustomers().get(0);
        customerFacade = new CustomerFacade();
        customerFacade.login(customer.getEmail(), customer.getPassword());

        try {
            System.out.println("TESTING LOGIN");
            LoginManager.getInstance().login(customer.getEmail(), customer.getPassword(), ClientType.CUSTOMER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING LOGIN FAIL");
        try {
            LoginManager.getInstance().login("my.email@com", "012345678", ClientType.CUSTOMER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING PURCHASE");
        try {
            Coupon coupon = new CouponsDBDAO().getOneCoupon(1);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("TESTING PURCHASE FAIL");
        try {
            Coupon coupon = new CouponsDBDAO().getOneCoupon(1);
            customerFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


        System.out.println("TESTING GET ALL PURCHASED");
        TablePrinter.print(customerFacade.getCustomerCoupons());
        System.out.println();


        System.out.println("TESTING GET PURCHASED BY CATEGORY");
        TablePrinter.print(customerFacade.getCustomerCoupons(Category.ELECTRICITY));
        System.out.println();


        System.out.println("TESTING GET PURCHASED BY MAX PRICE");
        TablePrinter.print(customerFacade.getCustomerCoupons(100));
        System.out.println();


        System.out.println("TESTING GET CUSTOMER DETAILS");
        TablePrinter.print(customerFacade.getCustomerDetails());
        System.out.println();

        System.out.println("*********************************************************************");
        System.out.println("                          DROPPING TABLES TEST");
        System.out.println("*********************************************************************");

        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_SCHEMA);
        System.out.println("DROPPED");
    }
}



