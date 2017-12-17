package jonamato.violet.view;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.Admin;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.account.User;
import jonamato.violet.store.Stores;
import jonamato.violet.view.admin.*;
import jonamato.violet.view.buyer.CartWindow;
import jonamato.violet.view.buyer.WalletWindow;
import jonamato.violet.view.owner.OwnerAddStore;
import jonamato.violet.view.store.StoreWindow;
import jonamato.violet.view.suggest.RejectBrand;
import jonamato.violet.view.suggest.RejectProduct;
import jonamato.violet.view.suggest.SuggestBrand;
import jonamato.violet.view.suggest.SuggestProduct;

public class ActionWindow extends AppWindow {

    public ActionWindow(App app, User user) {

        super(app,
                ((user == null)
                ? "Unregistered:"
                : (user.getClass().getSimpleName() + ": " + (user.getUsername())))
                + " Actions",
                user);

    }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox();

        actions.addItem("View Stores", () -> app.push(new StoresWindow(app, user).build()));

        if(user == null) {

            actions.addItem("Login", () -> app.push(new LoginWindow(app).build()));
            actions.addItem("Register", () -> app.push(new RegisterWindow(app).build()));

        }

        if(user instanceof Buyer) {

            actions.addItem("View Cart", () -> app.push(new CartWindow(app, user).build()));
            actions.addItem("View Wallet", () -> app.push(new WalletWindow(app, user).build()));

        }

        if(user instanceof Owner) {

            actions.addItem("Add Store", () -> app.push(new OwnerAddStore(app, user).build()));
            Stores.instance().all().stream()
                    .filter(s -> s.getOwnerID().equals(user.getUsername()))
                    .forEach(s -> actions.addItem("View " + s.getName(),
                            () -> app.push(new StoreWindow(app, user, s).build()))
                    );

        }

        if(user instanceof Admin) {

            actions.addItem("Add Brand", () -> app.push(new AdminAddBrand(app).build()));
            actions.addItem("Remove Brand", () -> app.push(new AdminRemoveBrand(app).build()));
            actions.addItem("Add Category", () -> app.push(new AdminAddCategory(app).build()));
            actions.addItem("Remove Category", () -> app.push(new AdminRemoveCategory(app).build()));
            actions.addItem("Add Product", () -> app.push(new AdminAddProduct(app).build()));
            actions.addItem("Remove Product", () -> app.push(new AdminRemoveProduct(app).build()));
            actions.addItem("Suggested Brands", () -> app.push(new RejectBrand(app).build()));
            actions.addItem("Suggested Products", () -> app.push(new RejectProduct(app).build()));
            actions.addItem("Provide Voucher", () -> app.push(new AdminAddVoucher(app).build()));

        }

        if(user instanceof Buyer || user instanceof Owner) {

            actions.addItem("Suggest Brand", () -> app.push(new SuggestBrand(app).build()));
            actions.addItem("Suggest Product", () -> app.push(new SuggestProduct(app).build()));

        }

        if(user != null) {

            actions.addItem("Logout", app::pop);

        }

        actions.addItem("Exit", app::exit);

        panel.addComponent(actions);

    }

}
