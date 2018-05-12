package unit.services.brand;

import jonamatoka.violet.data.model.Brand;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddBrandValidData extends TestVioletServices {

    @DataProvider(name = "brandValidDataProvider")
    public Object[][] brandValidDataProvider() {
        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };
    }

    @Test(dataProvider = "brandValidDataProvider")
    public void addBrandValidData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertTrue(addBrand(brand));
    }

}
