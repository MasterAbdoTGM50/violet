package jonamatoka.violet.product;

import jonamatoka.violet.util.NitriteHelper;

import java.util.List;

public class Products {

    private static Products instance;

    private Products() { };

    public static Products get() {

        if (null == instance) { instance = new Products(); }
        return instance;

    }

    public boolean add(Product product) {

        if (!NitriteHelper.get().insert(product, Product.class)) { return false; }

        return true;

    }

    public boolean update(Product product) { return NitriteHelper.get().update(product, Product.class); }

    public List<Product> all() { return NitriteHelper.get().all(Product.class); }

}
