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

    private boolean addCategory(Category category) {
        return categoryServices.add(category).getBody();
    }

    @DataProvider(name = "categoryValidDataProvider")
    public Object[][] categoryValidDataProvider() {
        return new Object[][] { {"Category_1"}, {"Category1"}, {"CATEGORY"} };
    }

    @Test(dataProvider = "categoryValidDataProvider")
    public void addCategoryValidData(String name) {
        Category category = new Category().setName(name);
        Assert.assertTrue(addCategory(category));
    }

    @Test(dataProvider = "categoryValidDataProvider", dependsOnMethods = "addCategoryValidData")
    public void addCategoryDuplicateData(String name) {
        Category category = new Category().setName(name);
        Assert.assertFalse(addCategory(category));
    }

    @DataProvider(name = "categoryInvalidDataProvider")
    public Object[][] categoryInvalidDataProvider() {
        return new Object[][] { {" "}, {"@A!<script>alert(\"die\");</script>"}, {"or 1=1"} };
    }

    @Test(dataProvider = "categoryInvalidDataProvider")
    public void addCategoryInvalidData(String name) {
        Category category = new Category().setName(name);
        Assert.assertFalse(addCategory(category));
    }

    @Test(dependsOnMethods = {"addCategoryValidData", "addCategoryDuplicateData", "addCategoryInvalidData"})
    public void getAllCategories() {
        Assert.assertEquals(categoryServices.all().getBody().size(), categoryRepository.count());
    }

}