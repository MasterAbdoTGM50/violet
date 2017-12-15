package account;

import misc.Address;
import product.Cart;
import wallet.IWallet;
import wallet.IWalletOwner;
import wallet.Wallet;

public class Buyer extends User implements IWalletOwner {

    private IWallet wallet = new Wallet();
    private Cart cart = new Cart();

    @Override
    public Buyer setUsername(String username) { return (Buyer)super.setUsername(username); }

    @Override
    public Buyer setEmail(String email) { return (Buyer)super.setEmail(email); }

    @Override
    public Buyer setPhone(String phone) { return (Buyer)super.setPhone(phone); }

    @Override
    public Buyer setAddress(Address address) { return (Buyer)super.setAddress(address); }

    @Override
    public IWallet getWallet() { return wallet; }

    public Cart getCart() { return cart; }

}
