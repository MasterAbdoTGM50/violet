package jonamato.violet.suggestion;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class ProductSuggestions {

    private static ProductSuggestions instance = new ProductSuggestions();

    private ProductSuggestions() { }

    public static ProductSuggestions instance() { return instance; }

    public boolean suggest(ProductSuggestion product) { return NitriteHelper.insert(product, ProductSuggestion.class); }

    public boolean remove(ProductSuggestion product) { return NitriteHelper.remove(product, ProductSuggestion.class); }

    public List<ProductSuggestion> all() { return NitriteHelper.all(ProductSuggestion.class); }

}
