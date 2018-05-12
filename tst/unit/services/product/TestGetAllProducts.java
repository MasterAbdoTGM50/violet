package unit.services.product;

import org.testng.Assert;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestGetAllProducts extends TestVioletServices {

    @Test
    public void getAllProducts() {
        Assert.assertEquals(productServices.all().getBody().size(), productRepository.count());
    }

}