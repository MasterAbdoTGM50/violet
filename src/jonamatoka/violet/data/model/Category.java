package jonamatoka.violet.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    private String name;

    public String getName() { return name; }

    public Category setName(String name) { this.name = name; return this; }

}
