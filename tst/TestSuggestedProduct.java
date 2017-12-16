import jonamato.violet.product.SuggestedProduct;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSuggestedProduct {

    private SuggestedProduct suggestedProduct;
    private SuggestedProducts suggestedProducts;

    @BeforeMethod
    public void initialize() {

        System.out.println("initialize()");
        suggestedProducts = SuggestedProducts.instance();
        suggestedProduct = new SuggestedProduct();
    }

    @Test()
    public void testSuggest() {

        System.out.println("testSuggest()");
        suggestedProducts.suggest(suggestedProduct.setName("A"));
        suggestedProducts.suggest(suggestedProduct.setName("S"));
        suggestedProducts.suggest(suggestedProduct.setName("D"));
    }

    @AfterMethod
    public void testAllSuggestedProduct() {

        System.out.println("testAllSuggestedProduct()");
        Assert.assertEquals(3, suggestedProducts.all().size());
    }

    @AfterTest
    public void clean() {

        System.out.println("clean()");
        suggestedProduct = null;
        suggestedProducts = null;
    }
}
