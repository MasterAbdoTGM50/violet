package unit.services.product;

import jonamatoka.violet.data.model.Product;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetProduct extends AbstractTestProductService {

    @Test
    public void getProduct() {
        boolean getOk = true;
        for (Product product : getProductRepository().findAll()) {
            if (getProductServices().get(product.getProductId()).getBody().getProductId() != product.getProductId()) {
                getOk = false;
                break;
            }
        }
        Assert.assertTrue(getOk);
    }

}