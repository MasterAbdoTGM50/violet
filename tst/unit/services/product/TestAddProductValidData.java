package unit.services.product;

import jonamatoka.violet.data.model.Product;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddProductValidData extends TestAddProduct {

    @DataProvider(name = "productValidDataProvider")
    public Object[][] productValidDataProvider() {
        return new Object[][] {
                {"Product_1", "Brand_1", "Category_1", "Desc1"},
                {"Product1", "Brand1", "Category1", "Desc2"},
                {"PRODUCT", "BRAND", "CATEGORY", "Desc3"}
        };
    }

    @Test(dataProvider = "productValidDataProvider")
    public void addProductValidData(String name, String brand, String category, String description) {
        Product product = new Product()
                .setName(name)
                .setBrand(brandRepository.findOne(brand))
                .setCategory(categoryRepository.findOne(category))
                .setDescription(description);

        Assert.assertTrue(addProduct(product));
    }

}
