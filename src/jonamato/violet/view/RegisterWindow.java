package jonamato.violet.view;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.account.Registry;
import jonamato.violet.account.User;

public class RegisterWindow extends AppWindow {

    public RegisterWindow(App app) { super(app, "Register"); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label userLabel = new Label("Username");
        TextBox userBox = new TextBox();

        Label emailLabel = new Label("Email");
        TextBox emailBox = new TextBox();

        Label passLabel = new Label("Password");
        TextBox passBox = new TextBox().setMask('*');

        Label typesLabel = new Label("Type");
        RadioBoxList<String> typesBox = new RadioBoxList<>();
        typesBox.addItem(Buyer.class.getSimpleName());
        typesBox.addItem(Owner.class.getSimpleName());
        typesBox.setCheckedItem(Buyer.class.getSimpleName());

        Button register = new Button("Register", () -> {

            if(userBox.getText().isEmpty()) { return; }
            if(emailBox.getText().isEmpty()) { return; }
            if(passBox.getText().isEmpty()) { return; }

            User user =
                    ((typesBox.getCheckedItem().equals(Owner.class.getSimpleName())) ? new Owner() : new Buyer())
                    .setUsername(userBox.getText())
                    .setEmail(emailBox.getText());

            if(!Registry.instance().register(user, passBox.getText())) { return; }

            app.pop();

        });

        panel.addComponent(userLabel);
        panel.addComponent(userBox);
        panel.addComponent(emailLabel);
        panel.addComponent(emailBox);
        panel.addComponent(passLabel);
        panel.addComponent(passBox);
        panel.addComponent(typesLabel);
        panel.addComponent(typesBox);
        panel.addComponent(new EmptySpace());
        panel.addComponent(register);

    }

}
