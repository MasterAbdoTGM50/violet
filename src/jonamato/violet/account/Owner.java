package jonamato.violet.account;

import jonamato.violet.store.Store;
import jonamato.violet.util.Address;
import jonamato.violet.wallet.IWalletOwner;
import jonamato.violet.wallet.Wallet;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User implements IWalletOwner {

    private Wallet wallet = new Wallet();
    private List<String> stores = new ArrayList<>();
    private boolean premium = false;

    @Override
    public Owner setUsername(String username) { return (Owner)super.setUsername(username); }

    @Override
    public Owner setHash(long hash) { return (Owner)super.setHash(hash); }

    @Override
    public Owner setEmail(String email) { return (Owner)super.setEmail(email); }

    @Override
    public Owner setPhone(String phone) { return (Owner)super.setPhone(phone); }

    @Override
    public Owner setAddress(Address address) { return (Owner)super.setAddress(address); }

    public boolean isPremium() { return premium; }

    public Owner setPremium(boolean premium) { this.premium = premium; return this; }

    @Override
    public Wallet getWallet() { return wallet; }

    public List<String> getStores() { return stores; }

    public void add(Store store) {

        store.setOwnerID(getUsername());
        stores.add(store.getName());
    }

    public void remove(Store store) { stores.remove(store.getName()); }

}
