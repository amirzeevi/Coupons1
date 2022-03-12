package dao;

import beans.Category;

/**
 * The CategoryDAO interface is to be implemented by the class
 * that should access the database and update the category table.
 */
public interface CategoriesDAO {
    /**
     * Will add all existing categories to the category table.
     */
    void addAllCategories();
    /**
     * Will add the specified category only if it does not already exist
     */
    void addOneCategory(Category category);
    /**
     * Will delete the specified category from the database
     */
    void deleteCategory(Category category);
}
