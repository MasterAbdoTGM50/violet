package jonamato.violet.view;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.Lib;
import jonamato.violet.account.Registry;

public class LoginWindow extends AppWindow {

    public LoginWindow(App app) { super(app, "Login"); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label userLabel = new Label("Username");
        TextBox userBox = new TextBox();

        Label passLabel = new Label("Password");
        TextBox passBox = new TextBox().setMask('*');

        Button login = new Button("Login", () -> {

            Lib.Platform.user = Registry.instance().login(userBox.getText(), passBox.getText());
            if(Lib.Platform.user != null) {

                app.push(new ActionWindow(app));

            } else { app.pop(); }

        });

        panel.addComponent(userLabel);
        panel.addComponent(userBox);
        panel.addComponent(passLabel);
        panel.addComponent(passBox);
        panel.addComponent(new EmptySpace());
        panel.addComponent(login);

    }

}
