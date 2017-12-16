package jonamato.violet.account;

import jonamato.violet.util.Address;
import org.dizitart.no2.objects.Id;

public class User {

    private final String type = this.getClass().getName();

    @Id
    private String username;
    private long hash;
    private String email;
    private String phone;
    private Address address;

    public String getUsername() { return username; }

    public User setUsername(String username) { this.username = username; return this; }

    public long getHash() { return hash; }

    public User setHash(long hash) { this.hash = hash; return this; }

    public String getEmail() { return email; }

    public User setEmail(String email) { this.email = email; return this; }

    public String getPhone() { return phone; }

    public User setPhone(String phone) { this.phone = phone; return this; }

    public Address getAddress() { return address; }

    public User setAddress(Address address) { this.address = address; return this; }

}
