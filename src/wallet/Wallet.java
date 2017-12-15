package wallet;

public class Wallet implements IWallet {

    private int balance = 0;

    @Override
    public double get() { return balance; }

    @Override
    public boolean check(int balance) { return this.balance >= balance; }

    @Override
    public void add(int balance) { this.balance += balance; }

    @Override
    public void deduct(int balance) { this.balance -= balance; }

}
