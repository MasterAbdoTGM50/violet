package jonamato.violet.product;

public class SuggestedProduct extends Product {

    @Override
    public SuggestedProduct setId(String id) { return (SuggestedProduct)super.setId(id); }

    @Override
    public SuggestedProduct setName(String name) { return (SuggestedProduct)super.setName(name); }

    @Override
    public SuggestedProduct setCategory(String category) { return (SuggestedProduct)super.setCategory(category); }

    @Override
    public SuggestedProduct setBrand(String brand) { return (SuggestedProduct)super.setBrand(brand); }

    @Override
    public SuggestedProduct setDescription(String description) { return (SuggestedProduct)super.setDescription(description); }

}
