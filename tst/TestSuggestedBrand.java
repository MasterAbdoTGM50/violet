import jonamato.violet.product.brand.SuggestedBrand;
import jonamato.violet.product.brand.SuggestedBrands;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestSuggestedBrand {
    private SuggestedBrand suggestedBrand;
    private SuggestedBrands suggestedBrands;

    @BeforeMethod
    public void initialize() {

        System.out.println("initialize()");
        suggestedBrands = SuggestedBrands.instance();
        suggestedBrand = new SuggestedBrand();
    }

    @Test()
    public void testSuggest() {

        System.out.println("testSuggest()");
        suggestedBrands.suggest(suggestedBrand.setName("Walton"));
        suggestedBrands.suggest(suggestedBrand.setName("Clevo"));
        suggestedBrands.suggest(suggestedBrand.setName("Doel"));
    }

    @AfterMethod
    public void testAllSuggestedBrand() {

        System.out.println("testAllSuggestedBrand()");
        Assert.assertEquals(3, suggestedBrands.all().size());
    }

    @AfterTest
    public void clean() {

        System.out.println("clean()");
        suggestedBrands = null;
        suggestedBrand = null;
    }
}
