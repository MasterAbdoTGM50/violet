package jonamato.violet.product.brand;

import org.dizitart.no2.objects.Id;

public class Brand {

    @Id
    private String name;

    public String getName() { return name; }

    public Brand setName(String name) { this.name = name; return this; }

}
