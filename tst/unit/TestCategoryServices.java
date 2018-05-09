package unit;

import jonamatoka.violet.data.model.Category;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.web.services.CategoryServices;

import org.mockito.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TestCategoryServices {

    private List<Category> categories = new ArrayList<>();

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServices categoryServices;

    @BeforeTest
    public void setup() {

        MockitoAnnotations.initMocks(this);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryServices = new CategoryServices(categoryRepository);

    }

    @DataProvider(name = "categoryValidDataProvider")
    public Object[][] categoryValidDataProvider() {

        return new Object[][] { {"Category_1"}, {"Category1"}, {"CATEGORY"} };

    }

    private Object addCategory(String name) {

        Category category = new Category().setName(name);
        when(categoryRepository.save(Matchers.any(Category.class))).thenReturn(category);
        categories.add(category);
        return categoryServices.add(category).getBody();

    }

    @Test(dataProvider = "categoryValidDataProvider")
    public void addCategoryValidData(String name) {

        Assert.assertEquals(addCategory(name), true);

    }

    @DataProvider(name = "categoryInvalidDataProvider")
    public Object[][] categoryInvalidDataProvider() {

        return new Object[][] { {" "}, {"@A!"}, {"or 1=1"} };

    }

    @Test(dataProvider = "categoryInvalidDataProvider")
    public void addCategoryInvalidData(String name) {

        Assert.assertEquals(addCategory(name), false);

    }


    @Test(dependsOnMethods = "addCategoryValidData")
    public void getAllCategories() {

        when(categoryRepository.findAll()).thenReturn(categories);
        Assert.assertEquals(categoryServices.all().getBody().equals(categories), true);

    }

}