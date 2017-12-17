package brand;

import jonamato.violet.suggestion.BrandSuggestion;
import jonamato.violet.suggestion.BrandSuggestions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestBrandSuggestion {
    static private int NUM_OF_SUGGESTED_BRANDS = 100;
    static private int REMOVE_NUM = 10;
    static private int SUGGESTED_BRAND_NAME_MAX_SZ = 32;
    static private int SUGGESTED_BRAND_NAME_MIN_SZ = 5;

    private BrandSuggestions BrandSuggestions;
    private BrandSuggestion[] BrandSuggestion = new BrandSuggestion[NUM_OF_SUGGESTED_BRANDS];

    // Random Name Generator
    String genRandName(int sz) {
        String ret = "";
        for (int i = 0; i < sz; ++i) {

            ret += "!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".charAt((int) (Math.random() * sz));
        }
        return ret;
    }

    @BeforeMethod
    public void init() {
        System.out.println("Init...");

        for (int i = 0; i < NUM_OF_SUGGESTED_BRANDS; ++i) {

            BrandSuggestion[i] = new BrandSuggestion();

            int range = (SUGGESTED_BRAND_NAME_MAX_SZ - SUGGESTED_BRAND_NAME_MIN_SZ) + 1;
            BrandSuggestion[i].setName(genRandName((int) (Math.random() * range) + SUGGESTED_BRAND_NAME_MIN_SZ));
        }
        BrandSuggestions = BrandSuggestions.instance();
    }

    @Test
    public void addAndRemoveBrandSuggestion() {

        System.out.println("Testing Suggested Brand Addition...");
        for (int i = 0; i < NUM_OF_SUGGESTED_BRANDS; ++i) {

            Assert.assertEquals(true, BrandSuggestions.suggest(BrandSuggestion[i]));
        }


        System.out.println("Asserting after addition...");
        System.out.println("Number of suggested brands : " + BrandSuggestions.all().size());

        Assert.assertEquals(NUM_OF_SUGGESTED_BRANDS, BrandSuggestions.all().size());


        System.out.println("Testing Remove suggested Brand...");
        for (int i = 0; i < REMOVE_NUM; ++i) {

            BrandSuggestions.remove(BrandSuggestion[i]);
        }

        System.out.println("Asserting after removing...");
        System.out.println("Number of suggested brands : " +  BrandSuggestions.all().size());

        Assert.assertEquals(NUM_OF_SUGGESTED_BRANDS - REMOVE_NUM, BrandSuggestions.all().size());

    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}
