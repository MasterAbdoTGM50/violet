package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.model.Category;
import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.web.services.BrandServices;
import jonamatoka.violet.web.services.CategoryServices;
import jonamatoka.violet.web.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@SpringBootTest(classes = App.class)
public class TestProductServices extends AbstractTestNGSpringContextTests {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandServices brandServices;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServices productServices;

    @BeforeClass
    void setup() {
        String[] mockBrands = {"Brand_1", "Brand1", "BRAND"};
        for (String mockBrand : mockBrands) {
            Brand brand = new Brand().setName(mockBrand);
            brandServices.add(brand);
        }

        String[] mockCategories = {"Category_1", "Category1", "CATEGORY"};
        for (String mockCategory : mockCategories) {
            Category category = new Category().setName(mockCategory);
            categoryServices.add(category);
        }
    }

    private boolean add(Product product) { return productServices.add(product).getBody(); }

    @DataProvider(name = "productValidDataProvider")
    public Object[][] productValidDataProvider() {
        return new Object[][] {
                {"Product_1", "Brand_1", "Category_1", "Desc1"},
                {"Product1", "Brand1", "Category1", "Desc2"},
                {"PRODUCT", "BRAND", "CATEGORY", "Desc3"}
        };
    }

    @Test(dataProvider = "productValidDataProvider")
    public void addValidData(String name, String brand, String category, String description) {
        Product product = new Product()
                .setName(name)
                .setBrand(brandRepository.findOne(brand))
                .setCategory(categoryRepository.findOne(category))
                .setDescription(description);

        Assert.assertTrue(add(product));
    }

    @Test(dataProvider = "productValidDataProvider", dependsOnMethods = "addValidData")
    public void addDuplicateData(String name, String brand, String category, String description) {
        Product product = new Product()
                .setName(name)
                .setBrand(brandRepository.findOne(brand))
                .setCategory(categoryRepository.findOne(category))
                .setDescription(description);

        Assert.assertFalse(add(product));
    }

    @DataProvider(name = "productInvalidDataProvider")
    public Object[][] productInvalidDataProvider() {
        return new Object[][] {
                {"Product_1", "Invalid Brand", "Invalid Category", "Desc1"}
        };
    }

    @Test(dataProvider = "productInvalidDataProvider")
    public void addProductInvalidData(String name, String brand, String category, String description)  {
        Product product = new Product()
                .setName(name)
                .setBrand(brandRepository.findOne(brand))
                .setCategory(categoryRepository.findOne(category))
                .setDescription(description);

        Assert.assertFalse(add(product));
    }

    @Test(dependsOnMethods = {"addValidData", "addDuplicateData", "addProductInvalidData"})
    public void all() {
        Assert.assertEquals(productServices.all().getBody().size(), productRepository.count());
    }

    @Test
    public void get() {
        boolean getOk = true;
        for (Product product : productRepository.findAll()) {
            if (productServices.get(product.getProductId()).getBody().getProductId() != product.getProductId()) {
                getOk = false;
                break;
            }
        }
        Assert.assertTrue(getOk);
    }

}