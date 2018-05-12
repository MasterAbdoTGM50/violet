package unit.services.brand;

import jonamatoka.violet.data.model.Brand;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddBrandDuplicateData extends TestVioletServices {

    @DataProvider(name = "brandDuplicateDataProvider")
    public Object[][] brandValidDataProvider() {
        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };
    }

    @Test(dataProvider = "brandDuplicateDataProvider")
    public void addBrandDuplicateData(String name) {
        Brand brand = new Brand().setName(name);
        addBrand(brand);
        Assert.assertFalse(addBrand(brand));
    }

}
