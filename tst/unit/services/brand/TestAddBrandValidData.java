package unit.services.brand;

import jonamatoka.violet.data.model.Brand;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddBrandValidData extends AbstractTestBrandService {

    @DataProvider(name = "brandValidDataProvider")
    public Object[][] brandValidDataProvider() {
        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };
    }

    @Test(dataProvider = "brandValidDataProvider")
    public void addBrandValidData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertTrue(getBrandServices().add(brand).getBody());
    }

}
