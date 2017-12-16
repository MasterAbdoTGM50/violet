package jonamato.violet.product.voucher;

import org.dizitart.no2.objects.Id;

public class Voucher {

    @Id
    private String code;
    private int uses;
    private double discount;

    public String getCode() { return code; }

    public Voucher setCode(String code) { this.code = code; return this; }

    public int getUses() { return uses; }

    public Voucher setUses(int uses) { this.uses = uses; return this; }

    public double getDiscount() { return discount; }

    public Voucher setDiscount(double discount) { this.discount = discount; return this; }

}