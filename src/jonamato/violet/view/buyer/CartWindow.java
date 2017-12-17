package jonamato.violet.view.buyer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Registry;
import jonamato.violet.account.User;
import jonamato.violet.product.Orders;
import jonamato.violet.product.ProductStack;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class CartWindow extends AppWindow {

    public CartWindow(App app, User user) { super(app, "Cart", user); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        CheckBoxList<ProductStack> products = new CheckBoxList<>(new TerminalSize(40, 10));
        ((Buyer)user).getCart().get().forEach(products::addItem);
        products.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2));

        Button remove = new Button("Remove", () -> {

            products.getCheckedItems().forEach(p -> Orders.instance().unorder((Buyer)user, p));
            app.pop();

        });

        Button checkout = new Button("Checkout", () -> app.push(new CheckoutWindow(app, user).build()))
                .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING));

        Button back = new Button("Back", app::pop);

        panel.addComponent(products);
        panel.addComponent(remove);
        panel.addComponent(checkout);
        panel.addComponent(back);

    }

}
