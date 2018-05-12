package unit.services.product;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetAllProducts extends AbstractTestProductService {

    @Test
    public void getAllProducts() {
        Assert.assertEquals(getProductServices().all().getBody().size(), getProductRepository().count());
    }

}