package unit.services.product;

import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.model.Category;

import org.testng.annotations.BeforeClass;

import unit.TestVioletServices;

public abstract class TestAddProduct extends TestVioletServices {

    @BeforeClass
    public void setup() {
        super.setup();

        String[] mockBrands = {"Brand_1", "Brand1", "BRAND"};
        for (String mockBrand : mockBrands) {
            Brand brand = new Brand().setName(mockBrand);
            addBrand(brand);
        }

        String[] mockCategories = {"Category_1", "Category1", "CATEGORY"};
        for (String mockCategory : mockCategories) {
            Category category = new Category().setName(mockCategory);
            addCategory(category);
        }
    }

}
