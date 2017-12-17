package jonamato.violet.store;

import jonamato.violet.account.Owner;
import jonamato.violet.account.Registry;
import jonamato.violet.product.ProductStack;
import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;
import java.util.Objects;

public class Stores {

    private static Stores instance = new Stores();

    private Stores() { }

    public static Stores instance() { return instance; }

    public boolean add(Owner owner, Store store) {

        if(!NitriteHelper.insert(store, Store.class)) { return false; }

        owner.add(store);
        Registry.instance().update(owner);
        update(store);

        return true;

    }

    public boolean add(Store store, ProductStack stack) {

        store.add(stack);
        return update(store);

    }

    public boolean view(Store store, ProductStack stack, int views) {

        stack.view(views);
        return update(store);

    }

    public boolean update(Store store) { return NitriteHelper.update(store, Store.class); }

    public boolean remove(Store store) {

        Owner owner =
                (Owner)Registry.instance().all()
                .stream()
                .filter(u -> Objects.equals(u.getUsername(), store.getOwnerID()))
                .findFirst().orElse(null);

        if (owner != null) {

            owner.remove(store);
            Registry.instance().update(owner);

        }

        return NitriteHelper.remove(store, Store.class);

    }

    public List<Store> all() { return NitriteHelper.all(Store.class); }

}
