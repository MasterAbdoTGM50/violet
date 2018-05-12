package unit.services.product;

import jonamatoka.violet.data.model.Product;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddProductInvalidData extends TestAddProduct {

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
                .setBrand(getBrandRepository().findOne(brand))
                .setCategory(getCategoryRepository().findOne(category))
                .setDescription(description);

        Assert.assertFalse(getProductServices().add(product).getBody());
    }

}
