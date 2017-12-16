package jonamato.violet.product;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class Products {

    private static Products instance = new Products();

    private Products() { }

    public static Products instance() { return instance; }

    public boolean add(Product product) { return NitriteHelper.insert(product, Product.class); }

    public boolean update(Product product) { return NitriteHelper.update(product, Product.class); }

    public boolean remove(Product product) { return NitriteHelper.remove(product, Product.class); }

    public List<Product> all() { return NitriteHelper.all(Product.class); }

}
