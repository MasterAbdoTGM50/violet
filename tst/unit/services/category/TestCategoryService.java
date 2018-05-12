package unit.services.category;

import jonamatoka.violet.data.model.Category;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.web.services.CategoryServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.TestVioletService;

public abstract class TestCategoryService extends TestVioletService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServices categoryServices;

    public CategoryRepository getCategoryRepository() { return categoryRepository; }

    public CategoryServices getCategoryServices() { return categoryServices; }

}
