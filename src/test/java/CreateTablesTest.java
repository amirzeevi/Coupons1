import db.DBmanager;
import db.DBrunQuery;
import org.junit.Test;

public class CreateTablesTest {

    @Test
    public void createAll() {
        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CUSTOMERS_COUPONS_TABLE);
    }

    @Test
    public void dropAll(){
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CUSTOMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.DROP_SCHEMA);
    }
}
