package jonamatoka.violet.data.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Cart {

    @ElementCollection
    @JsonUnwrapped
    private List<ProductStack> products = new ArrayList<>();

    public List<ProductStack> get() { return products; }

    public ProductStack add(ProductStack stack) {

        stack.setKey(stack.getProductId() + ":" + stack.getQuantity() + ":" + stack.getPrice());
        this.products.add(stack);
        return stack;

    }

    public ProductStack remove(ProductStack stack) {

        ProductStack pStack = this.findByKey(stack.getKey());
        this.products.remove(pStack);
        return pStack;

    }

    public ProductStack findByKey(String key) {

        return products.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElse(null);

    }

    public void clear() { products.clear(); }

}