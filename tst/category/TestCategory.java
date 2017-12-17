package category;

import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCategory {

    static private int NUM_OF_CATEGORIES = 100;
    static private int REMOVE_NUM = 10;
    static private int CATEGORY_NAME_MAX_SZ = 32;
    static private int CATEGORY_NAME_MIN_SZ = 5;

    private Categories categories;
    private Category[] category = new Category[NUM_OF_CATEGORIES];

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

        int range = (CATEGORY_NAME_MAX_SZ - CATEGORY_NAME_MIN_SZ) + 1;
        for (int i = 0; i < NUM_OF_CATEGORIES; ++i) {

            category[i] = new Category();
            category[i].setName(genRandName((int) (Math.random() * range) + CATEGORY_NAME_MIN_SZ));
        }
        categories = Categories.instance();
    }

    @Test
    public void addAndRemoveCategory() {

        System.out.println("Testing Category Addition...");
        for (int i = 0; i < NUM_OF_CATEGORIES; ++i) {

            Assert.assertEquals(true, categories.add(category[i]));
        }


        System.out.println("Asserting after addition...");
        System.out.println("Number of Categories : " +  categories.all().size());

        Assert.assertEquals(NUM_OF_CATEGORIES, categories.all().size());


        System.out.println("Testing Remove Category...");
        for (int i = 0; i < REMOVE_NUM; ++i) {

            categories.remove(category[i]);
        }


        System.out.println("Asserting after removing...");
        System.out.println("Number of Categories : " +  categories.all().size());

        Assert.assertEquals(NUM_OF_CATEGORIES - REMOVE_NUM, categories.all().size());
    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void addCategory() {

        System.out.println("category name shouldn't be empty...");

        category[0].setName("");
        categories.add(category[0]);
    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}
