package jonamatoka.violet.util.action;

import jonamatoka.violet.data.model.Store;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StoreAction extends Action<Store> {

    @Override
    public StoreAction setId(long id) { super.setId(id); return this; }

}
