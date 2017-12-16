package jonamato.violet.product.brand;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;

public class SuggestedBrands {

    private static SuggestedBrands instance = new SuggestedBrands();

    private SuggestedBrands() { }

    public static SuggestedBrands instance() { return instance; }

    public boolean suggest(SuggestedBrand brand) { return NitriteHelper.insert(brand, SuggestedBrand.class); }

    public boolean remove(SuggestedBrand brand) { return NitriteHelper.remove(brand, SuggestedBrand.class); }

    public List<SuggestedBrand> all() { return NitriteHelper.all(SuggestedBrand.class); }

}
