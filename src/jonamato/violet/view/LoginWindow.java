package jonamato.violet.view;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.account.Registry;
import jonamato.violet.account.User;

public class LoginWindow extends AppWindow {

    public LoginWindow(App app) { super(app, "Login", null); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label userLabel = new Label("Username");
        TextBox userBox = new TextBox();

        Label passLabel = new Label("Password");
        TextBox passBox = new TextBox().setMask('*');

        Button login = new Button("Login", () -> {

            User user = Registry.instance().login(userBox.getText(), passBox.getText());
            if(user != null) {

                app.push(new ActionWindow(app, user).init());

            } else { app.pop(); }

        });

        Button cancel = new Button("Cancel", app::exit);

        panel.addComponent(userLabel);
        panel.addComponent(userBox);
        panel.addComponent(passLabel);
        panel.addComponent(passBox);
        panel.addComponent(new EmptySpace());
        panel.addComponent(login);
        panel.addComponent(new EmptySpace());
        panel.addComponent(cancel);

    }

}
