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

    public boolean order(Buyer buyer, Store store, ProductStack stack, int quantity) {

        if(quantity > stack.getQuantity()) return false;

        stack.order(quantity);
        buyer.getCart().add(
                new ProductStack()
                .setProductID(stack.getProductID())
                .setProductName(stack.getProductName())
                .setStoreID(stack.getStoreID())
                .setQuantity(quantity)
                .setPrice(stack.getPrice())
                .setDate(new Date())
        );

        Registry.instance().update(buyer);
        Stores.instance().update(store);

        return true;

    }

    public boolean unorder(Buyer buyer, ProductStack stack) {

        Store store = Stores.instance().all().stream()
                .filter(s -> s.getName().equals(stack.getStoreID()))
                .findFirst()
                .orElse(null);

        if (store != null) {

            ProductStack product = store.getInventory().get().stream()
                    .filter(p -> p.getProductID().equals(stack.getProductID()))
                    .findFirst()
                    .orElse(null);

            if (product != null) {

                product.setQuantity(product.getQuantity() + stack.getQuantity());
                buyer.getCart().remove(stack);

                Registry.instance().update(buyer);
                Stores.instance().update(store);

            }

        }


        return true;

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
