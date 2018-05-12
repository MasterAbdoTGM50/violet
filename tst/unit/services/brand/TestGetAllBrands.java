package unit.services.brand;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestGetAllBrands extends AbstractTestBrandService {

    @Test
    public void getAllBrands() {
        Assert.assertEquals(getBrandServices().all().getBody().size(), getBrandRepository().count());
    }

}