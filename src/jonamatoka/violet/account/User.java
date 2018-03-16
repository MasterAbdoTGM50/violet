package jonamatoka.violet.account;

import jonamatoka.violet.product.Cart;
import jonamatoka.violet.util.Wallet;
import org.dizitart.no2.objects.Id;

public class User {

    @Id
    private String username;
    private long hash;
    private String email;
    private int privileges;

    private Wallet wallet = new Wallet();
    private Cart cart = new Cart();

    public String getUsername() { return username; }

    public User setUsername(String username) { this.username = username; return this; }

    public long getHash() { return hash; }

    public User setHash(long hash) { this.hash = hash; return this; }

    public String getEmail() { return email; }

    public User setEmail(String email) { this.email = email; return this; }

    public int getPriviliges() { return privileges; }

    public User setPriviliges(int privileges) { this.privileges = privileges; return this; }

    public Wallet getWallet() { return wallet; }

    public Cart getCart() { return cart; }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hash=" + hash +
                ", email='" + email + '\'' +
                ", privileges=" + privileges +
                '}';
    }

}
