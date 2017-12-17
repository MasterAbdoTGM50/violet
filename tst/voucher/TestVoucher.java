package voucher;

import jonamato.violet.product.voucher.Voucher;
import jonamato.violet.product.voucher.Vouchers;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestVoucher {

    static private int NUM_OF_VOUCHER = 100;
    static private int REMOVE_NUM = 10;
    static private int VOUCHER_CODE_MAX_SZ = 32;
    static private int VOUCHER_CODE_MIN_SZ = 5;

    private Vouchers vouchers;
    private Voucher[] voucher = new Voucher[NUM_OF_VOUCHER];
    private String[] code = new String[NUM_OF_VOUCHER];

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

        int range = (VOUCHER_CODE_MAX_SZ - VOUCHER_CODE_MIN_SZ) + 1;
        for (int i = 0; i < NUM_OF_VOUCHER; ++i) {

            voucher[i] = new Voucher();

            code[i] = genRandName((int) (Math.random() * range) + VOUCHER_CODE_MIN_SZ);
            voucher[i].setCode(code[i]);
            voucher[i].setDiscount((Math.random() * range) + VOUCHER_CODE_MIN_SZ);
            voucher[i].setUses((int) (Math.random() * range) + VOUCHER_CODE_MIN_SZ);
        }
        vouchers = Vouchers.instance();
    }

    @Test(priority = 1)
    public void addAndRemoveVoucher() {

        System.out.println("Testing Voucher Addition...");
        for (int i = 0; i < NUM_OF_VOUCHER; ++i) {

            Assert.assertEquals(true, vouchers.add(voucher[i]));
        }


        System.out.println("Asserting after addition...");
        System.out.println("Number of Vouchers : " + vouchers.all().size());

        Assert.assertEquals(NUM_OF_VOUCHER, vouchers.all().size());


        System.out.println("Testing Remove Voucher...");
        for (int i = 0; i < REMOVE_NUM; ++i) {

            vouchers.remove(voucher[i]);
        }


        System.out.println("Asserting after removing...");
        System.out.println("Number of Vouchers : " + vouchers.all().size());

        Assert.assertEquals(NUM_OF_VOUCHER - REMOVE_NUM, vouchers.all().size());

    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void addEmptyVoucher() {

        System.out.println("Voucher code shouldn't be empty...");

        voucher[0].setCode("");
        vouchers.add(voucher[0]);
    }

    @Test(priority = 2)
    public void updateVoucher() {

        System.out.println("Testing update Voucher...");

        Voucher p = new Voucher();
        p.setCode(voucher[0].getCode());

        voucher[0].setCode("319.284.302.339");

        Assert.assertEquals(true, vouchers.update(voucher[0]));
        Assert.assertNotEquals(p.getCode(), vouchers.all().get(0).getCode());

        System.out.println("Before update : " + p.getCode());
        System.out.println("After update : " + voucher[0].getCode());

    }

    @Test(priority = 3)
    public void useVoucher() {

        Vouchers v = Vouchers.instance();
        v.add(voucher[0]);

        System.out.println("Testing use Voucher...");
        Assert.assertEquals(voucher[0].getDiscount(), v.use(voucher[0].getCode()));
    }

    @Test
    public void useVoucherNoUsers() {

        // number of users = 0 mean remove the voucher card :)
        voucher[0].setUses(0);

        Vouchers v = Vouchers.instance();
        v.add(voucher[0]);
        v.use(voucher[0].getCode());

        System.out.println("No Users...");
        Assert.assertEquals(0, v.all().size());
    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}