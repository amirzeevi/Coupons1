import DB.DBmanager;
import DB.DBrunQuery;

public class Program {

    public static void main(String[] args) {
//        Test.testAll();
        DBrunQuery.runQuery(DBmanager.CREATE_COSTUMERS_COUPONS_TABLE);
    }
}
