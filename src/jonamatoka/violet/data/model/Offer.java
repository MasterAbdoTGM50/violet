package jonamatoka.violet.data.model;

import javax.persistence.Embeddable;

@Embeddable
public class Offer {

    private long stackId;
    private double discount;

    public long getStackId() { return stackId; }

    public Offer setStackId(long stackId) { this.stackId = stackId; return this; }

    public double getDiscount() { return discount; }

    public Offer setDiscount(double discount) { this.discount = discount; return this; }

    @Override
    public String toString() { return "{ " + getStackId() + ":" + getDiscount() + " }"; }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Offer) { return this.getStackId() == ((Offer)obj).getStackId() && this.getDiscount() == ((Offer) obj).getDiscount(); }
        return false;

    }

}
