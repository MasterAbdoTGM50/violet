package jonamato.violet.view.buyer;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.User;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class WalletWindow extends AppWindow {

    public WalletWindow(App app, User user) { super(app, "Wallet", user); }

    @Override
    protected void init(Panel panel) {

        if(user instanceof Buyer) {

            panel.addComponent(new Label("Balance: " + ((Buyer)user).getWallet().get()));

        }

        panel.addComponent(new Button("Back", app::pop));

    }

}
