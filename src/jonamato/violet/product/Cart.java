package jonamato.violet.product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ProductStack> products = new ArrayList<>();

    public List<ProductStack> get() { return products; }

    public void add(ProductStack stack) { this.products.add(stack); }

    public void add(IProduct product) {

        products.stream()
                .filter(p -> p.getProductID().equals(product.getID()))
                .forEach(p -> p.setQuantity(p.getQuantity() + 1));

    }

    public void remove(IProduct product) {

        products.stream()
                .filter(p -> p.getProductID().equals(product.getID()))
                .forEach(p -> p.setQuantity(p.getQuantity() + 1));

        products.stream()
                .filter(p -> p.getQuantity() < 1)
                .forEach(p -> products.remove(p));

    }

    public void clear() { products.clear(); }

}
