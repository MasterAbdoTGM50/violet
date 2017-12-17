package jonamato.violet.view.store;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.Owner;
import jonamato.violet.account.User;
import jonamato.violet.product.ProductStack;
import jonamato.violet.store.Store;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;
import jonamato.violet.view.owner.OwnerAddProduct;

import java.util.Comparator;

public class StoreWindow extends AppWindow {

    private Store store;

    public StoreWindow(App app, User user, Store store) {

        super(app, store.getName() + ": Actions", user);
        this.store = store;

    }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox();

        actions.addItem("View Products", () -> app.push(new StoreProductsWindow(app, user, store).build()));

        if(user instanceof Owner) {

            if(((Owner)user).isPremium()) {

                ProductStack viewed = store.getInventory().get()
                        .stream()
                        .max(Comparator.comparing(ProductStack::views))
                        .orElse(null);

                ProductStack ordered = store.getInventory().get()
                        .stream()
                        .max(Comparator.comparing(ProductStack::orders))
                        .orElse(null);

                if(viewed != null) { panel.addComponent(new Label("Most Viewed:  " + viewed.getProductName())); }
                if(ordered != null) { panel.addComponent(new Label("Most Ordered: " + ordered.getProductName())); }

            }

            if(user.getUsername().equals(store.getOwnerID())) {

                actions.addItem("Add Product", () -> app.push(new OwnerAddProduct(app, store).build()));

            }

        }

        actions.addItem("Back", app::pop);

        panel.addComponent(actions);

    }

}
