package unit.services.brand;

import org.testng.Assert;
import org.testng.annotations.*;

import unit.TestVioletServices;

public class TestGetAllBrands extends TestVioletServices {

    @Test
    public void getAllBrands() {
        Assert.assertEquals(allBrands().size(), brandRepository.count());
    }

}