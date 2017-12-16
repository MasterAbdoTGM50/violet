package jonamato.violet.view;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.Admin;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.account.User;
import jonamato.violet.view.admin.*;
import jonamato.violet.view.owner.OwnerAddStore;
import jonamato.violet.view.suggest.RejectBrand;
import jonamato.violet.view.suggest.RejectProduct;
import jonamato.violet.view.suggest.SuggestBrand;
import jonamato.violet.view.suggest.SuggestProduct;

public class ActionWindow extends AppWindow {

    public ActionWindow(App app, User user) {

        super(app,
                (user == null)
                ? "Unregistered:"
                : (user.getClass().getSimpleName() + ": " + (user.getUsername()))
                + " Actions",
                user);

    }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox();

        if(user == null) {

            actions.addItem("Login", () -> app.push(new LoginWindow(app).init()));
            actions.addItem("Register", () -> app.push(new RegisterWindow(app).init()));

        }

        if(user instanceof Owner) {

            actions.addItem("Add Store", () -> app.push(new OwnerAddStore(app, user).init()));

        }

        if(user instanceof Admin) {

            actions.addItem("Add Brand", () -> app.push(new AdminAddBrand(app).init()));
            actions.addItem("Remove Brand", () -> app.push(new AdminRemoveBrand(app).init()));
            actions.addItem("Add Category", () -> app.push(new AdminAddCategory(app).init()));
            actions.addItem("Remove Category", () -> app.push(new AdminRemoveCategory(app).init()));
            actions.addItem("Add Product", () -> app.push(new AdminAddProduct(app).init()));
            actions.addItem("Remove Product", () -> app.push(new AdminRemoveProduct(app).init()));
            actions.addItem("Suggested Brands", () -> app.push(new RejectBrand(app).init()));
            actions.addItem("Suggested Products", () -> app.push(new RejectProduct(app).init()));
            actions.addItem("Provide Voucher", () -> app.push(new AdminAddVoucher(app).init()));

        }

        if(user instanceof Buyer || user instanceof Owner) {

            actions.addItem("Suggest Brand", () -> app.push(new SuggestBrand(app).init()));
            actions.addItem("Suggest Product", () -> app.push(new SuggestProduct(app).init()));

        }

        if(user != null) {

            actions.addItem("Logout", app::pop);

        }

        actions.addItem("Exit", app::exit);

        panel.addComponent(actions);

    }

}
