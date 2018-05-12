package unit.services.category;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetAllCategories extends TestCategoryService {

    @Test
    public void getAllCategories() {
        Assert.assertEquals(getCategoryServices().all().getBody().size(), getCategoryRepository().count());
    }

}