package jonamato.violet.view.store;

import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.account.User;
import jonamato.violet.store.Store;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class StoreWindow extends AppWindow {

    Store store;

    public StoreWindow(App app, User user, Store store) {

        super(app, "Store View", user);
        this.store = store;

    }

    @Override
    protected void init(Panel panel) {

        if(user instanceof Buyer) {

        }

        if(user instanceof Owner) {

        }

    }

}
