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

    private boolean add(Brand brand) {
        return brandServices.add(brand).getBody();
    }

    @DataProvider(name = "brandValidDataProvider")
    public Object[][] brandValidDataProvider() {
        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };
    }

    @Test(dataProvider = "brandValidDataProvider")
    public void addValidData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertTrue(add(brand));
    }

    @Test(dataProvider = "brandValidDataProvider", dependsOnMethods = "addValidData")
    public void addDuplicateData(String name) {
        Brand brand = new Brand().setName(name);
        Assert.assertFalse(add(brand));
    }

    @DataProvider(name = "brandInvalidDataProvider")
    public Object[][] brandInvalidDataProvider() {
        return new Object[][] { {" "}, {"@A!"}, {"or 1=1"} };
    }

   @Test(dataProvider = "brandInvalidDataProvider")
    public void addInvalidData(String name) {
       Brand brand = new Brand().setName(name);
       Assert.assertFalse(add(brand));
    }

    @Test(dependsOnMethods = {"addValidData", "addDuplicateData", "addInvalidData"})
    public void all() {
        Assert.assertEquals(brandServices.all().getBody().size(), brandRepository.count());
    }

}