package jonamato.violet.product.brand;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class Brands {

    private static Brands instance = new Brands();

    private Brands() { }

    public static Brands instance() { return instance; }

    public boolean add(Brand brand) { return NitriteHelper.insert(brand, Brand.class); }

    public boolean remove(Brand brand) { return NitriteHelper.remove(brand, Brand.class); }

    public List<Brand> all() { return NitriteHelper.all(Brand.class); }

}
