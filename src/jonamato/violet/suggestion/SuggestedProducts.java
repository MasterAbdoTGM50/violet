package jonamato.violet.suggestion;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class SuggestedProducts {

    private static SuggestedProducts instance = new SuggestedProducts();

    private SuggestedProducts() { }

    public static SuggestedProducts instance() { return instance; }

    public boolean suggest(ProductSuggestion product) { return NitriteHelper.insert(product, ProductSuggestion.class); }

    public boolean remove(ProductSuggestion product) { return NitriteHelper.remove(product, ProductSuggestion.class); }

    public List<ProductSuggestion> all() { return NitriteHelper.all(ProductSuggestion.class); }

}
