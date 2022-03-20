import db.DBmanager;
import db.DBrunQuery;

import dbdao.CategoriesDBDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
/**
 * Test class for creating and dropping schema and tables in database.
 */

public class TablesTest {

    @Test
    public void createAll() {
        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.SET_TIME_ZONE);
        DBrunQuery.runQuery(DBmanager.CREATE_TRIGGER_COUPON_PURCHASE);
    }

    @Test
    public void dropAll() {
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_SCHEMA);
    }
}
