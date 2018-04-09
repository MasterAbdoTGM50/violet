package jonamatoka.violet.data.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Cart {

    @ElementCollection
    private List<ProductStack> products = new ArrayList<>();

    public List<ProductStack> get() { return products; }

    public void add(ProductStack stack) { this.products.add(stack); }

    public void add(Product product) {

        products.stream()
                .filter(p -> p.getProductId() == product.getId())
                .forEach(p -> p.setQuantity(p.getQuantity() + 1));

    }

    public void remove(ProductStack stack) { this.products.remove(stack); }

    public void remove(Product product) {

        products.stream()
                .filter(p -> p.getProductId() == product.getId())
                .forEach(p -> p.setQuantity(p.getQuantity() - 1));

        products.stream()
                .filter(p -> p.getQuantity() < 1)
                .forEach(p -> products.remove(p));

    }

    public void clear() { products.clear(); }

}