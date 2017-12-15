package account;

import misc.Address;
import org.dizitart.no2.objects.Id;

public class User {

    private final String type = this.getClass().getName();

    @Id
    private String username;
    private String email;
    private String phone;
    private Address address;

    public String getUsername() { return username; }

    public User setUsername(String username) { this.username = username; return this; }

    public String getEmail() { return email; }

    public User setEmail(String email) { this.email = email; return this; }

    public String getPhone() { return phone; }

    public User setPhone(String phone) { this.phone = phone; return this; }

    public Address getAddress() { return address; }

    public User setAddress(Address address) { this.address = address; return this; }

}
