package jonamatoka.violet.util.action;

import jonamatoka.violet.data.model.ProductStack;
import jonamatoka.violet.data.model.Store;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class StoreRemProductAction extends StoreAction {

    @NotNull
    @Embedded
    private ProductStack stack;

    @Override
    public void exec(Store actionee) {

        if(this.state != State.EXECUTED) {
            this.stack = actionee.getInventory().remove(stack);
            this.state = State.EXECUTED;
        }

    }

    @Override
    public void unexec(Store actionee) {

        if(this.state != State.UNEXECUTED) {
            this.stack = actionee.getInventory().add(stack);
            this.state = State.UNEXECUTED;
        }

    }

    @Override
    public StoreRemProductAction setId(long id) { super.setId(id); return this; }

    public ProductStack getStack() { return this.stack; }

    public StoreRemProductAction setStack(ProductStack stack) { this.stack = stack; return this; }

    @Override
    public String getDescrition() { return "Removed Stack " + this.stack; }

}
