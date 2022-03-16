import dbdao.CategoriesDBDAO;
import beans.Category;
import dao.CategoriesDAO;
import org.junit.Test;

/**
 * Test class for the categories table
 */
public class CategoryTest {

    CategoriesDAO categoriesDAO = new CategoriesDBDAO();

    @Test
    public void addAllCategories() {
        this.categoriesDAO.addAllCategories();
    }

    @Test
    public void addOneCategory() {
        Category categoryToAdd = Category.values()[(int) (Math.random() * Category.values().length)];
        this.categoriesDAO.addOneCategory(categoryToAdd);
    }

    @Test
    public void deleteCategory() {
        Category categoryToDelete = Category.HOLIDAY;
        this.categoriesDAO.deleteCategory(categoryToDelete);
    }
}
