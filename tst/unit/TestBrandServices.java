package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.web.services.BrandServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.*;

@SpringBootTest(classes = App.class)
public class TestBrandServices extends AbstractTestNGSpringContextTests {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandServices brandServices;

    private boolean addBrand(Brand brand) {
        return brandServices.add(brand).getBody();
    }

    @DataProvider(name = "brandValidDataProvider")
    public Object[][] brandValidDataProvider() {
        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };
    }

    @Test(dataProvider = "brandValidDataProvider")
    public void addBrandValidData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertTrue(addBrand(brand));
    }

    @Test(dataProvider = "brandValidDataProvider", dependsOnMethods = "addBrandValidData")
    public void addBrandDuplicateData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertFalse(addBrand(brand));
    }

    @DataProvider(name = "brandInvalidDataProvider")
    public Object[][] brandInvalidDataProvider() {
        return new Object[][] { {" "}, {"@A!"}, {"or 1=1"} };
    }

   @Test(dataProvider = "brandInvalidDataProvider")
    public void addBrandInvalidData(String name) {
       Brand brand = new Brand().setName(name);
       Assert.assertFalse(addBrand(brand));
    }

    @Test(dependsOnMethods = {"addBrandValidData", "addBrandDuplicateData", "addBrandInvalidData"})
    public void getAllBrands() {
        Assert.assertEquals(brandServices.all().getBody().size(), brandRepository.count());
    }

}