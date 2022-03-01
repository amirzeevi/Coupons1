import DB.DBmanager;
import DB.DBrunQuery;
import beans.Category;

import java.util.Map;

public class Program {

    public static void main(String[] args) {
//        Test.testAll();

        DBrunQuery.runQuery(DBmanager.CREATE_SCHEMA);
        DBrunQuery.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COSTUMERS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        DBrunQuery.runQuery(DBmanager.CREATE_COSTUMERS_COUPONS_TABLE);

        for (Category category : Category.values()) {
            Map<Integer, Object> values = Map.of(1, category);
            DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, values);
        }

    }

}
