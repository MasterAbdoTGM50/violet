import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCategories {
    private Categories categories;
    private Category category;

    @BeforeMethod
    public void initialize() {

        System.out.println("initialize()");
        categories = Categories.instance();
        category = new Category();
    }

    @Test()
    public void testAddCategory() {

        System.out.println("testAddCategory()");
        categories.add(category.setName("A"));
        categories.add(category.setName("B"));
        categories.add(category.setName("C"));
    }

    @AfterMethod
    public void testAllCategories() {

        System.out.println("testAllCategories()");
        Assert.assertEquals(3, categories.all().size());
    }

    @AfterTest
    public void clean() {

        System.out.println("clean()");
        category = null;
        categories = null;
    }
}