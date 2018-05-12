package unit.services.product;

import jonamatoka.violet.data.model.Product;

import org.testng.Assert;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestGetProduct extends TestVioletServices {

    @Test
    public void getProduct() {
        boolean getOk = true;
        for (Product product : productRepository.findAll()) {
            if (productServices.get(product.getProductId()).getBody().getProductId() != product.getProductId()) {
                getOk = false;
                break;
            }
        }
        Assert.assertTrue(getOk);
    }

}