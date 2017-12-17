package product;

import jonamato.violet.suggestion.ProductSuggestion;
import jonamato.violet.suggestion.ProductSuggestions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSuggestedProduct {


    static private int NUM_OF_SUGGESTED_PRODUCTS = 100;
    static private int REMOVE_NUM = 10;
    static private int SUGGESTED_PRODUCT_NAME_MAX_SZ = 32;
    static private int SUGGESTED_PRODUCT_NAME_MIN_SZ = 5;

    private  ProductSuggestions ProductSuggestions;
    private ProductSuggestion[] productSuggestion = new ProductSuggestion[NUM_OF_SUGGESTED_PRODUCTS];

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

        for (int i = 0; i < NUM_OF_SUGGESTED_PRODUCTS; ++i) {

            productSuggestion[i] = new ProductSuggestion();

            int range = (SUGGESTED_PRODUCT_NAME_MAX_SZ - SUGGESTED_PRODUCT_NAME_MIN_SZ) + 1;
            productSuggestion[i].setID(i + "");
            productSuggestion[i].setName(genRandName((int) (Math.random() * range) + SUGGESTED_PRODUCT_NAME_MIN_SZ));
        }
        ProductSuggestions = ProductSuggestions.instance();
    }

    @Test
    public void addAndRemoveSuggestedProduct() {

        System.out.println("Testing Suggested Product Addition...");
        for (int i = 0; i < NUM_OF_SUGGESTED_PRODUCTS; ++i) {

            Assert.assertEquals(true, ProductSuggestions.suggest(productSuggestion[i]));
        }


        System.out.println("Asserting after addition...");
        System.out.println("Number of suggested products : " +  ProductSuggestions.all().size());

        Assert.assertEquals(NUM_OF_SUGGESTED_PRODUCTS, ProductSuggestions.all().size());


        System.out.println("Testing Remove suggested Product...");
        for (int i = 0; i < REMOVE_NUM; ++i) {

            ProductSuggestions.remove(productSuggestion[i]);
        }

        System.out.println("Asserting after removing...");
        System.out.println("Number of suggested products : " +  ProductSuggestions.all().size());

        Assert.assertEquals(NUM_OF_SUGGESTED_PRODUCTS - REMOVE_NUM, ProductSuggestions.all().size());

    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}
