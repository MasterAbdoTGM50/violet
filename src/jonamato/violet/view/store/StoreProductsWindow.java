package jonamato.violet.view.store;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.User;
import jonamato.violet.store.Store;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;
import jonamato.violet.view.product.ProductViewWindow;

public class StoreProductsWindow extends AppWindow {

    private Store store;

    public StoreProductsWindow(App app, User user, Store store) {

        super(app, "Products", user);
        this.store = store;

    }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox(new TerminalSize(40, 10));
        actions.addItem("Product\tQty\tPrice", () -> { });
        store.getInventory().get().forEach(
                p -> actions.addItem(p.toString(), () -> app.push(new ProductViewWindow(app, user, store, p).build()))
        );

        Button back = new Button("Back", app::pop);

        panel.addComponent(actions);
        panel.addComponent(back);

    }

}
