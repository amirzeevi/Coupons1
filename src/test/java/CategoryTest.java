
import DBdao.CategoryDBDAO;
import beans.Category;
import dao.CategoryDAO;
import org.junit.Test;

public class CategoryTest {

    CategoryDAO categoryDAO = new CategoryDBDAO();

    @Test
    public void addAllCategories() {
        this.categoryDAO.addAllCategories();
    }

    @Test
    public void addOneCategory() {
        Category categoryToAdd = Category.values()[(int) (Math.random() * Category.values().length)];
        this.categoryDAO.addOneCategory(categoryToAdd);
    }

    @Test
    public void deleteCategory() {
//        Category categoryToDelete = Category.values()[(int) (Math.random() * Category.values().length)];
        Category categoryToDelete = Category.FOOD;
        this.categoryDAO.deleteOneCategory(categoryToDelete);
    }
}
