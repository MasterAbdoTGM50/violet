import jonamato.violet.product.voucher.Voucher;
import jonamato.violet.product.voucher.Vouchers;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestVoucher {

    private Voucher voucher;
    private Vouchers vouchers;

    @BeforeMethod
    public void initialize() {

        vouchers = Vouchers.instance();
        voucher = new Voucher();

        voucher.setCode("111");
        voucher.setUses(0);
        voucher.setDiscount(222.5);
    }

    @DataProvider(name = "addVoucher")
    public Object[][] addVoucher() {

        System.out.println("addVoucher");
        return new Object[][] {
                { true, new Voucher().setCode("12345").setUses(20).setDiscount(100.0) },
                { false, new Voucher().setCode("12345").setUses(20).setDiscount(100.0) },
                { true, new Voucher().setCode("6789").setUses(30).setDiscount(140.0) },
                { true, new Voucher().setCode("1357").setUses(22).setDiscount(90.0) },
        };
    }

    @Test(dataProvider = "addVoucher")
    public void testAddVoucher(boolean expectedResult, Voucher v) {

        System.out.println("testAddVoucher()");

        System.out.println(v.getCode() + " ?add => " + true);
        Assert.assertEquals(expectedResult, vouchers.add(v));
    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void testAddVoucher2() {

        System.out.println("testAddVoucher2()");

        // brand name is empty :)
        voucher.setCode("");
        Assert.assertEquals(false, vouchers.add(voucher));
    }

    @DataProvider(name = "useVoucher")
    public Object[][] useVoucher() {

        System.out.println("useVoucher");
        return new Object[][]{
                { 100.0, "12345"},
                { 140.0, "6789" },
                { 90.0, "1357" },
                { 0.0, "2223" },      // not found
        };
    }

    @Test(dataProvider = "useVoucher")
    public void testUseVoucher(double expectedResult, String code) {

        System.out.println("testUseVoucher()");

        System.out.println(code + " ?discount => " + expectedResult);
        Assert.assertEquals(expectedResult, vouchers.use(code));
    }

    @Test
    public void testUseVoucher2() {

        // number of users = 0
        Assert.assertEquals(0, vouchers.use(voucher.getCode()), 0.0);
    }

    @Test(priority = 2)
    public void testListOfAllVouchers() {

        System.out.println("testListOfAllVouchers()");
        Assert.assertEquals(3, vouchers.all().size());
    }
    @AfterMethod
    public void clean() {

        vouchers = null;
        voucher = null;
    }
}