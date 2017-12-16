package jonamato.violet.account;

import jonamato.violet.product.Cart;
import jonamato.violet.util.Address;
import jonamato.violet.wallet.IWalletOwner;
import jonamato.violet.wallet.Wallet;

public class Buyer extends User implements IWalletOwner {

    private Wallet wallet = new Wallet();
    private Cart cart = new Cart();

    @Override
    public Buyer setUsername(String username) { return (Buyer)super.setUsername(username); }

    @Override
    public Buyer setHash(long hash) { return (Buyer)super.setHash(hash); }

    @Override
    public Buyer setEmail(String email) { return (Buyer)super.setEmail(email); }

    @Override
    public Buyer setPhone(String phone) { return (Buyer)super.setPhone(phone); }

    @Override
    public Buyer setAddress(Address address) { return (Buyer)super.setAddress(address); }

    @Override
    public Wallet getWallet() { return wallet; }

    public Cart getCart() { return cart; }

}
