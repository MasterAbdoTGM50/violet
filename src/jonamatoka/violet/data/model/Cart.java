package jonamatoka.violet.data.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.openhft.hashing.LongHashFunction;

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

        stack.setStackId(LongHashFunction.xx().hashChars(stack.getProductId() + ":" + stack.getQuantity() + ":" + stack.getPrice()));
        this.products.add(stack);
        return stack;

    }

    public ProductStack remove(ProductStack stack) {

        ProductStack pStack = this.findById(stack.getStackId());
        this.products.remove(pStack);
        return pStack;

    }

    public ProductStack findById(long key) {

        return products.stream()
                .filter(p -> p.getStackId() == key)
                .findFirst()
                .orElse(null);

    }

    public void clear() { products.clear(); }

}