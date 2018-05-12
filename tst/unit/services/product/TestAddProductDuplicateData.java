package unit.services.product;

import jonamatoka.violet.data.model.Product;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddProductDuplicateData extends TestAddProduct {

    @DataProvider(name = "productDuplicateDataProvider")
    public Object[][] productDuplicateDataProvider() {
        return new Object[][] {
                {"Product_1", "Brand_1", "Category_1", "Desc1"},
                {"Product1", "Brand1", "Category1", "Desc2"},
                {"PRODUCT", "BRAND", "CATEGORY", "Desc3"}
        };
    }

    @Test(dataProvider = "productDuplicateDataProvider")
    public void addProductDuplicateData(String name, String brand, String category, String description) {
        Product product = new Product()
                .setName(name)
                .setBrand(getBrandRepository().findOne(brand))
                .setCategory(getCategoryRepository().findOne(category))
                .setDescription(description);
        
        getProductServices().add(product);
        Assert.assertFalse(getProductServices().add(product).getBody());
    }

}
