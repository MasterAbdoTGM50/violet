package unit.services.product;

import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.model.Category;

import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.data.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import unit.TestVioletService;

public abstract class TestAddProduct extends TestProductService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeClass
    public void setup() {
        super.setup();

        String[] mockBrands = {"Brand_1", "Brand1", "BRAND"};
        for (String mockBrand : mockBrands) {
            Brand brand = new Brand().setName(mockBrand);
            brandRepository.save(brand);
        }

        String[] mockCategories = {"Category_1", "Category1", "CATEGORY"};
        for (String mockCategory : mockCategories) {
            Category category = new Category().setName(mockCategory);
            categoryRepository.save(category);
        }
    }

    public BrandRepository getBrandRepository() { return brandRepository; }

    public CategoryRepository getCategoryRepository() { return categoryRepository; }

}
