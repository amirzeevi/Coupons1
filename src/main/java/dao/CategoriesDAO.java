package dao;

import beans.Category;

public interface CategoriesDAO {

    void addAllCategories();

    void addOneCategory(Category category);

    void deleteCategory(Category category);
}
