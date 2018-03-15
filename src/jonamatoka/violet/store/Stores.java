package jonamatoka.violet.store;

import jonamatoka.violet.util.NitriteHelper;

import java.util.List;

public class Stores {

    private static Stores instance;

    private Stores() { };

    public static Stores get() {

        if (null == instance) { instance = new Stores(); }
        return instance;

    }

    public boolean add(Store store) {

        if (!NitriteHelper.get().insert(store, Store.class)) { return false; }

        return true;

    }

    public Store find(String id) {

        Store ret = null;

        for (Store store : all()) { if (store.getId().equals(id)) { ret = store; break; } }

        return ret;

    }

    public boolean update(Store store) { return NitriteHelper.get().update(store, Store.class); }

    public List<Store> all() { return NitriteHelper.get().all(Store.class); }

}
