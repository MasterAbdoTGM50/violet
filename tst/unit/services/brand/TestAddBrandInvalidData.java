package unit.services.brand;

import jonamatoka.violet.data.model.Brand;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddBrandInvalidData extends TestVioletServices {

    @DataProvider(name = "brandInvalidDataProvider")
    public Object[][] brandInvalidDataProvider() {
        return new Object[][] { {" "}, {"@A!"}, {"or 1=1"} };
    }

    @Test(dataProvider = "brandInvalidDataProvider")
    public void addBrandInvalidData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertFalse(addBrand(brand));
    }

}
