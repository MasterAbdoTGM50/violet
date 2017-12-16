package jonamato.violet.suggestion;

import org.dizitart.no2.objects.Id;

public class BrandSuggestion {

    @Id
    private String name;

    public String getName() { return name; }

    public BrandSuggestion setName(String name) { this.name = name; return this; }

    @Override
    public String toString() { return name; }

}
