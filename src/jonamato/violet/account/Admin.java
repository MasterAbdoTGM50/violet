package jonamato.violet.account;

import jonamato.violet.util.Address;

public class Admin extends User {

    @Override
    public Admin setUsername(String username) { return (Admin)super.setUsername(username); }

    @Override
    public Admin setHash(long hash) { return (Admin)super.setHash(hash); }

    @Override
    public Admin setEmail(String email) { return (Admin)super.setEmail(email); }

    @Override
    public Admin setPhone(String phone) { return (Admin)super.setPhone(phone); }

    @Override
    public Admin setAddress(Address address) { return (Admin)super.setAddress(address); }

}
