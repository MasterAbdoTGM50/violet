package jonamatoka.violet.product;

import org.dizitart.no2.objects.Id;

public class Category {

    @Id
    private String name;

    public String getName() { return name; }

    public Category setName(String name) { this.name = name; return this; }

    @Override
    public String toString() { return name; }

}
