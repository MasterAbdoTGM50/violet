package unit.services.brand;

import jonamatoka.violet.data.model.Brand;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddBrandDuplicateData extends TestBrandsService {

    @DataProvider(name = "brandDuplicateDataProvider")
    public Object[][] brandValidDataProvider() {
        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };
    }

    @Test(dataProvider = "brandDuplicateDataProvider")
    public void addBrandDuplicateData(String name) {
        Brand brand = new Brand().setName(name);
        getBrandServices().add(brand);
        Assert.assertFalse(getBrandServices().add(brand).getBody());
    }

}
