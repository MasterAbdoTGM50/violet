package jonamato.violet.product.brand;

import org.dizitart.no2.objects.Id;

public class Brand {

    @Id
    protected String name;

    public String getName() { return name; }

    public Brand setName(String name) { this.name = name; return this; }

    @Override
    public String toString() { return name; }

}
