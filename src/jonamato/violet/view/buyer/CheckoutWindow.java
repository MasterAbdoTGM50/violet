package jonamato.violet.view.buyer;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.User;
import jonamato.violet.product.Orders;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class CheckoutWindow extends AppWindow {

    public CheckoutWindow(App app, User user) { super(app, "Checkout", user); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label voucherLabel = new Label("Voucher");
        TextBox voucherBox = new TextBox();

        Button back = new Button("Back", app::pop);
        Button checkout = new Button("Checkout", () -> {

            if(!Orders.instance().checkout((Buyer)user, voucherBox.getText()));
            app.pop();

        });

        panel.addComponent(voucherLabel);
        panel.addComponent(voucherBox);
        panel.addComponent(back);
        panel.addComponent(checkout);

    }

}
