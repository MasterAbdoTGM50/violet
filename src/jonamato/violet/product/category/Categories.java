package jonamato.violet.product.category;

import jonamato.violet.product.brand.Brand;
import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class Categories {

    private static Categories instance = new Categories();

    private Categories() { }

    public static Categories instance() { return instance; }

    public boolean add(Category category) { return NitriteHelper.insert(category, Category.class); }

    public boolean remove(Category category) { return NitriteHelper.remove(category, Category.class); }

    public List<Category> all() { return NitriteHelper.all(Category.class); }

}
