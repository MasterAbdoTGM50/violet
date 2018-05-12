package unit.services.category;

import jonamatoka.violet.data.model.Category;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddCategoryInvalidData extends TestVioletServices {

    @DataProvider(name = "categoryInvalidDataProvider")
    public Object[][] categoryInvalidDataProvider() {
        return new Object[][] { {" "}, {"@A!<script>alert(\"die\");</script>"}, {"or 1=1"} };
    }

    @Test(dataProvider = "categoryInvalidDataProvider")
    public void addCategoryInvalidData(String name) {
        Category category = new Category().setName(name);
        Assert.assertFalse(addCategory(category));
    }

}
