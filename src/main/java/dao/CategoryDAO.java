package dao;

import beans.Category;

public interface CategoryDAO {

     void addAllCategories();

     void addOneCategory(Category category);

     void deleteCategory(Category category);
}
