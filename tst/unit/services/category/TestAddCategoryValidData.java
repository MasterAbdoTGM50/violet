package unit.services.category;

import jonamatoka.violet.data.model.Category;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddCategoryValidData extends AbstractTestCategoryService {

    @DataProvider(name = "categoryValidDataProvider")
    public Object[][] categoryValidDataProvider() {
        return new Object[][] { {"Category_1"}, {"Category1"}, {"CATEGORY"} };
    }

    @Test(dataProvider = "categoryValidDataProvider")
    public void addCategoryValidData(String name) {
        Category category = new Category().setName(name);
        Assert.assertTrue(getCategoryServices().add(category).getBody());
    }

}
