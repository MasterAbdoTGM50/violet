package jonamatoka.violet.store;

import jonamatoka.violet.util.NitriteHelper;

import java.util.List;

public class Stores {

    private static Stores instance = new Stores();

    private Stores() { }

    public static Stores instance() { return instance; }

    public boolean add(Store store) {

        if (!NitriteHelper.get().insert(store, Store.class)) { return false; }

        return true;

    }

    public List<Store> all() { return NitriteHelper.get().all(Store.class); }

}
