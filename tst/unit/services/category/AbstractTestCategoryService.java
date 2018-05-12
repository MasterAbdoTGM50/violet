package unit.services.category;

import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.web.services.CategoryServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.AbstractTestVioletService;

public abstract class AbstractTestCategoryService extends AbstractTestVioletService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServices categoryServices;

    public CategoryRepository getCategoryRepository() { return categoryRepository; }

    public CategoryServices getCategoryServices() { return categoryServices; }

}
