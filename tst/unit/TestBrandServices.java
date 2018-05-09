package unit;

import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.web.services.BrandServices;

import org.mockito.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TestBrandServices {

    private List<Brand> brands = new ArrayList<>();

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServices brandServices;

    @BeforeTest
    public void setup() {

        MockitoAnnotations.initMocks(this);
        brandRepository = Mockito.mock(BrandRepository.class);
        brandServices = new BrandServices(brandRepository);

    }

    @DataProvider(name = "brandValidDataProvider")
    public Object[][] brandValidDataProvider() {

        return new Object[][] { {"Brand_1"}, {"Brand1"}, {"BRAND"} };

    }

    private Object addBrand(String name) {

        Brand brand = new Brand().setName(name);
        when(brandRepository.save(Matchers.any(Brand.class))).thenReturn(brand);
        brands.add(brand);
        return brandServices.add(brand).getBody();

    }

    @Test(dataProvider = "brandValidDataProvider")
    public void addBrandValidData(String name) {

        Assert.assertEquals(addBrand(name), true);

    }

    @DataProvider(name = "brandInvalidDataProvider")
    public Object[][] brandInvalidDataProvider() {

        return new Object[][] { {" "}, {"@A!"}, {"or 1=1"} };

    }

   @Test(dataProvider = "brandInvalidDataProvider")
    public void addBrandInvalidData(String name) {

       Assert.assertEquals(addBrand(name), false);

    }

    @Test(dependsOnMethods = "addBrandValidData")
    public void getAllBrands() {

        when(brandRepository.findAll()).thenReturn(brands);
        Assert.assertEquals(brandServices.all().getBody().equals(brands), true);

    }

}