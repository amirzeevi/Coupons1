package dbdao;

import db.DBmanager;
import db.DBrunQuery;
import beans.Category;
import dao.CategoryDAO;

import java.util.Arrays;
import java.util.Map;
/**
 * The CategoryDBDAO is the class that should access the database and update the category table.
 */
public class CategoryDBDAO implements CategoryDAO {
    /**
     * Will add all existing categories to the category table.
     */
    @Override
    public void addAllCategories() {
        Arrays.stream(Category.values()).forEach(category ->
            DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, Map.of(1, category)));
    }
    /**
     * Will add the specified category only if it does not already exist
     */
    @Override
    public void addOneCategory(Category category) {
        DBrunQuery.runQuery(DBmanager.ADD_CATEGORY, Map.of(1, category));
    }
    /**
     * Will delete the specified category from the database
     */
    @Override
    public void deleteCategory(Category category) {
        DBrunQuery.runQuery(DBmanager.DELETE_CATEGORY, Map.of(1, category));
    }
}
