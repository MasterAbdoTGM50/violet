package jonamato.violet.view;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.Lib;
import jonamato.violet.account.Admin;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.view.admin.*;
import jonamato.violet.view.owner.OwnerAddStore;
import jonamato.violet.view.suggest.RejectBrand;
import jonamato.violet.view.suggest.SuggestBrand;

public class ActionWindow extends AppWindow {

    public ActionWindow(App app) {

        super(app, ((Lib.Platform.user == null)
                ? "Unregistered:"
                : (Lib.Platform.user.getClass().getSimpleName() + ": " + (Lib.Platform.user.getUsername())))
                + " Actions");

    }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox();

        if(Lib.Platform.user == null) {

            actions.addItem("Login", () -> app.push(new LoginWindow(app)));
            actions.addItem("Register", () -> app.push(new RegisterWindow(app)));

        }

        if(Lib.Platform.user instanceof Owner) {

            actions.addItem("Add Store", () -> app.push(new OwnerAddStore(app)));

        }

        if(Lib.Platform.user instanceof Admin) {

            actions.addItem("Add Brand", () -> app.push(new AdminAddBrand(app)));
            actions.addItem("Remove Brand", () -> app.push(new AdminRemoveBrand(app)));
            actions.addItem("Add Category", () -> app.push(new AdminAddCategory(app)));
            actions.addItem("Remove Category", () -> app.push(new AdminRemoveCategory(app)));
            actions.addItem("Add Product", () -> app.push(new AdminAddProduct(app)));
            actions.addItem("Remove Product", () -> app.push(new AdminRemoveProduct(app)));
            actions.addItem("Suggested Brands", () -> app.push(new RejectBrand(app)));
            actions.addItem("Provide Voucher", () -> app.push(new AdminAddVoucher(app)));

        }

        if(Lib.Platform.user instanceof Buyer || Lib.Platform.user instanceof Owner) {

            actions.addItem("Suggest Brand", () -> app.push(new SuggestBrand(app)));

        }

        actions.addItem("Exit", app::exit);

        panel.addComponent(actions);

    }

}
