import jonamato.violet.account.Buyer;
import jonamato.violet.account.Registry;
import jonamato.violet.product.Orders;

import jonamato.violet.product.ProductStack;
import jonamato.violet.product.voucher.Voucher;
import jonamato.violet.product.voucher.Vouchers;
import jonamato.violet.store.Store;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrdersTest {
    private Buyer buyer;
    private Store store;
    private ProductStack pStack;

    @BeforeMethod
    public void init() {
        buyer = new Buyer()
                .setUsername("Mourad")
                .setEmail("y@gmail.com");

        buyer.getWallet().add(1000);

        Registry.instance().register(buyer, "password");

        store = new Store()
                .setName("Marmatos Takos");

        pStack = new ProductStack()
                .setProductName("Toffee")
                .setPrice(500)
                .setQuantity(10);

        store.add(pStack);

        Vouchers.instance().add(new Voucher()
                .setCode("A926959!")
                .setDiscount(100)
                .setUses(1));
    }

    @Test
    public void testOrder() throws Exception {
        Orders.instance().order(buyer, store, store.getInventory().get().get(0), 1);
        Assert.assertEquals(buyer.getCart().get().size(), 1);
        Assert.assertEquals(store.getInventory().get().get(0).getQuantity(), 9);

        Orders.instance().checkout(buyer, "A926959");
        Assert.assertEquals(buyer.getWallet().get(), 800.0);
        Assert.assertEquals(buyer.getCart().get().size(), 0);
    }
}