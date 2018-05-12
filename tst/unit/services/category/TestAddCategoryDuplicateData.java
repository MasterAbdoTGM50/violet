package unit.services.category;

import jonamatoka.violet.data.model.Category;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddCategoryDuplicateData extends TestVioletServices {

    @DataProvider(name = "categoryDuplicateDataProvider")
    public Object[][] categoryDuplicateDataProvider() {
        return new Object[][] { {"Category_1"}, {"Category1"}, {"CATEGORY"} };
    }

    @Test(dataProvider = "categoryDuplicateDataProvider")
    public void addCategoryDuplicateData(String name) {
        Category category = new Category().setName(name);
        addCategory(category);
        Assert.assertFalse(addCategory(category));
    }

}
