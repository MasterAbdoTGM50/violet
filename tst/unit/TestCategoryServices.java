package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.data.model.Category;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.web.services.CategoryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@SpringBootTest(classes = App.class)
public class TestCategoryServices extends AbstractTestNGSpringContextTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServices categoryServices;

    private boolean add(Category category) {
        return categoryServices.add(category).getBody();
    }

    @DataProvider(name = "categoryValidDataProvider")
    public Object[][] categoryValidDataProvider() {
        return new Object[][] { {"Category_1"}, {"Category1"}, {"CATEGORY"} };
    }

    @Test(dataProvider = "categoryValidDataProvider")
    public void addValidData(String name) {
        Category category = new Category().setName(name);
        Assert.assertTrue(add(category));
    }

    @Test(dataProvider = "categoryValidDataProvider", dependsOnMethods = "addValidData")
    public void addDuplicateData(String name) {
        Category category = new Category().setName(name);
        Assert.assertFalse(add(category));
    }

    @DataProvider(name = "categoryInvalidDataProvider")
    public Object[][] categoryInvalidDataProvider() {
        return new Object[][] { {" "}, {"@A!<script>alert(\"die\");</script>"}, {"or 1=1"} };
    }

    @Test(dataProvider = "categoryInvalidDataProvider")
    public void addInvalidData(String name) {
        Category category = new Category().setName(name);
        Assert.assertFalse(add(category));
    }

    @Test(dependsOnMethods = {"addValidData", "addDuplicateData", "addInvalidData"})
    public void all() {
        Assert.assertEquals(categoryServices.all().getBody().size(), categoryRepository.count());
    }

}