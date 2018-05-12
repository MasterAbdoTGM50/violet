package unit.services.product;

import org.testng.Assert;
import org.testng.annotations.Test;

import unit.TestVioletService;

public class TestGetAllProducts extends TestProductService {

    @Test
    public void getAllProducts() {
        Assert.assertEquals(getProductServices().all().getBody().size(), getProductRepository().count());
    }

}