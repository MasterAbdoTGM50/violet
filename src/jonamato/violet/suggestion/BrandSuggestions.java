package jonamato.violet.suggestion;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class BrandSuggestions {

    private static BrandSuggestions instance = new BrandSuggestions();

    private BrandSuggestions() { }

    public static BrandSuggestions instance() { return instance; }

    public boolean suggest(BrandSuggestion brand) { return NitriteHelper.insert(brand, BrandSuggestion.class); }

    public boolean remove(BrandSuggestion brand) { return NitriteHelper.remove(brand, BrandSuggestion.class); }

    public List<BrandSuggestion> all() { return NitriteHelper.all(BrandSuggestion.class); }

}
