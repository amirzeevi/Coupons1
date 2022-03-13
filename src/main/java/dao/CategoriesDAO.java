package dao;

import beans.Category;

/**
 * The CategoriesDAO interface is to be implemented by the class
 * that should access the database and update the categories table.
 */
public interface CategoriesDAO {
    /**
     * Will add all existing categories to the categories table.
     */
    void addAllCategories();
    /**
     * Will add the specified category only if it does not already exist in the database
     */
    void addOneCategory(Category category);
    /**
     * Will delete the specified category from the database
     */
    void deleteCategory(Category category);
}
