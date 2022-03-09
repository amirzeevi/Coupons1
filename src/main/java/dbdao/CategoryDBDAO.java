package dbdao;

import db.DBmanager;
import db.DBrunQuery;
import beans.Category;
import dao.CategoryDAO;

import java.util.Arrays;
import java.util.Map;

public class CategoryDBDAO implements CategoryDAO {

    @Override
    public void addAllCategories() {
        Arrays.stream(Category.values()).forEach(category -> {
            Map<Integer, Object> values = Map.of(1, category);
            DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, values);
        });
    }

    @Override
    public void addOneCategory(Category category) {
        Map<Integer, Object> values = Map.of(1, category);
        DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, values);
    }

    @Override
    public void deleteCategory(Category category) {
        Map<Integer, Object> values = Map.of(1, category);
        DBrunQuery.runQuery(DBmanager.DELETE_CATEGORY, values);
    }
}
