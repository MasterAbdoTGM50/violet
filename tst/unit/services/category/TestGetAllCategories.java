package unit.services.category;

import org.testng.Assert;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestGetAllCategories extends TestVioletServices {

    @Test
    public void getAllCategories() {
        Assert.assertEquals(allBrands().size(), categoryRepository.count());
    }

}