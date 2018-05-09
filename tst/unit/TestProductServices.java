package unit;

import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.model.Category;
import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.repo.ProductRepository;

import jonamatoka.violet.web.services.ProductServices;
import org.mockito.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TestProductServices {

    private List<Brand> brands = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    @Mock
    private ProductRepository brandRepository;

    @Mock
    private ProductRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServices productServices;

    @BeforeTest
    public void setup() {

        MockitoAnnotations.initMocks(this);
        productRepository = Mockito.mock(ProductRepository.class);
        productServices = new ProductServices(productRepository);

        brands.add(new Brand().setName("Brand_1"));
        brands.add(new Brand().setName("Brand1"));
        brands.add(new Brand().setName("BRAND"));

        categories.add(new Category().setName("Category_1"));
        categories.add(new Category().setName("Category1"));
        categories.add(new Category().setName("CATEGORY"));

    }

    @DataProvider(name = "productValidDataProvider")
    public Object[][] productValidDataProvider() {

        return new Object[][] {

                {0, "Product_1", brands.get(0), categories.get(0), "Desc1"},
                {1, "Product1", brands.get(1), categories.get(1), "Desc2"},
                {2, "PRODUCT", brands.get(2), categories.get(2), "Desc3"}

        };

    }

    private Object addProduct(long id, String name, Brand brand, Category category, String description) {

        Product product = new Product()
                .setProductId(id)
                .setName(name)
                .setBrand(brand)
                .setCategory(category)
                .setDescription(description);

        /*TODO//Mourad: mock product/brand/category repository FindOne return*/

        when(productRepository.save(Matchers.any(Product.class))).thenReturn(product);
        products.add(product);

        return productServices.add(product).getBody();

    }

    @Test(dataProvider = "productValidDataProvider")
    public void addProductValidData(long id, String name, Brand brand, Category category, String description) {

        Assert.assertEquals(addProduct(id, name, brand, category, description), true);

    }

    @DataProvider(name = "productInvalidDataProvider")
    public Object[][] productInvalidDataProvider() {

        return new Object[][] {

                {0, "Product_1", new Brand().setName("Invalid Brand"), new Category().setName("Invalid Category"), "Desc1"}

        };

    }

    @Test(dataProvider = "productInvalidDataProvider")
    public void addProductInvalidData(long id, String name, Brand brand, Category category, String description)  {

        Assert.assertEquals(addProduct(id, name, brand, category, description), false);

    }

    @Test(dependsOnMethods = "addProductValidData")
    public void getAllProducts() {

        when(productRepository.findAll()).thenReturn(products);
        Assert.assertEquals(productServices.all().getBody().equals(products), true);

    }

}