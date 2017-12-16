package jonamato.violet.product.voucher;

import jonamato.violet.util.helper.NitriteHelper;

import java.util.List;
import java.util.Objects;

public class Vouchers {

    private static Vouchers instance = new Vouchers();

    private Vouchers() { }

    public static Vouchers instance() { return instance; }

    public boolean add(Voucher voucher) { return NitriteHelper.insert(voucher, Voucher.class); }

    public boolean remove(Voucher voucher) { return NitriteHelper.remove(voucher, Voucher.class); }

    public boolean update(Voucher voucher) { return NitriteHelper.update(voucher, Voucher.class); }

    public double use(String code) {

        double discount = 0;
        Voucher voucher = all().stream()
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst()
                .orElse(null);

        if(voucher != null) {

            discount = voucher.getDiscount();
            voucher.setUses(voucher.getUses() - 1);
            if(voucher.getUses() <= 0) { remove(voucher); }
            update(voucher);

        }

        return discount;

    }

    public List<Voucher> all() { return NitriteHelper.all(Voucher.class); }

}
