package jonamatoka.violet.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand {

    @Id
    private String name;

    public String getName() { return name; }

    public Brand setName(String name) { this.name = name; return this; }

}
