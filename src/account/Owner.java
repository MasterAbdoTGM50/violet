package account;

import misc.Address;
import product.Cart;
import store.IStore;
import wallet.IWallet;
import wallet.IWalletOwner;
import wallet.Wallet;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User implements IWalletOwner {

    private IWallet wallet = new Wallet();
    private List<String> stores = new ArrayList<>();

    @Override
    public Owner setUsername(String username) { return (Owner)super.setUsername(username); }

    @Override
    public Owner setEmail(String email) { return (Owner)super.setEmail(email); }

    @Override
    public Owner setPhone(String phone) { return (Owner)super.setPhone(phone); }

    @Override
    public Owner setAddress(Address address) { return (Owner)super.setAddress(address); }

    @Override
    public IWallet getWallet() { return wallet; }

    public List<String> getStores() { return stores; }

    public void add(IStore store) { stores.add(store.getID()); }

    public void remove(IStore store) { stores.remove(store.getID()); }

}
