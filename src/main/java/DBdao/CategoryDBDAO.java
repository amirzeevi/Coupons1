package DBdao;

import DB.DBmanager;
import DB.DBrunQuery;
import beans.Category;
import dao.CategoryDAO;

import java.util.Map;

public class CategoryDBDAO implements CategoryDAO {

    @Override
    public void addAllCategories() {
        for (Category category : Category.values()) {
            Map<Integer, Object> values = Map.of(1, category);
            DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, values);
        }
    }

    @Override
    public void addOneCategory(Category category) {
        Map<Integer, Object> values = Map.of(1, category);
        DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, values);
    }

    @Override
    public void deleteOneCategory(Category category) {
        Map<Integer, Object> values = Map.of(1, category);
        DBrunQuery.runQuery(DBmanager.DELETE_CATEGORY, values);
    }
}
