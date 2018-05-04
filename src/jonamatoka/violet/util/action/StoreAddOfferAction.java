package jonamatoka.violet.util.action;

import jonamatoka.violet.data.model.Offer;
import jonamatoka.violet.data.model.Store;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class StoreAddOfferAction extends StoreAction {

    @NotNull
    @Embedded
    private Offer offer;

    @Override
    public void exec(Store actionee) {

        if(this.state != State.EXECUTED) {
            actionee.getOffers().add(this.offer);
            this.state = State.EXECUTED;
        }

    }

    @Override
    public void unexec(Store actionee) {

        if(this.state != State.UNEXECUTED) {
            actionee.getOffers().remove(this.offer);
            this.state = State.UNEXECUTED;
        }

    }

    @Override
    public StoreAddOfferAction setId(long id) { super.setId(id); return this; }

    public Offer getOffer() { return this.offer; }

    public StoreAddOfferAction setOffer(Offer offer) { this.offer = offer; return this; }

    @Override
    public String getDescrition() { return "Added Offer " + this.offer; }

}
