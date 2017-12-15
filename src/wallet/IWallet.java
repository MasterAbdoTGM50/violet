package wallet;

public interface IWallet {

    double get();

    boolean check(int balance);

    void add(int balance);

    void deduct(int balance);

}
