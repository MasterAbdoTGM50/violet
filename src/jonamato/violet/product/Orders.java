package jonamato.violet.product;

import jonamato.violet.account.Buyer;
import jonamato.violet.account.Registry;
import jonamato.violet.product.voucher.Vouchers;
import jonamato.violet.store.Store;
import jonamato.violet.store.Stores;

import java.util.Date;

public class Orders {

    private static Orders instance = new Orders();

    private Orders() { }

    public static Orders instance() { return instance; }

    public void buy(Buyer buyer, Store store, ProductStack stack, int quantity) {

        if(quantity > stack.getQuantity()) return;

        stack.order(quantity);
        buyer.getCart().add(new ProductStack().setProductID(stack.getProductID()).setQuantity(quantity).setDate(new Date()));

        Registry.instance().update(buyer);
        Stores.instance().update(store);

    }

    public boolean checkout(Buyer buyer, String voucher) {

        double total = 0;
        for(ProductStack stack: buyer.getCart().get()) { total += stack.getTotalPrice(); }
        total -= Vouchers.instance().use(voucher);

        if(!buyer.getWallet().check(total)) { return false; }

        buyer.getWallet().deduct(total);
        buyer.getCart().clear();

        Registry.instance().update(buyer);

        return true;

    }

}
