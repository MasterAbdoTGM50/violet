package jonamato.violet.view;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;

public class MainWindow extends AppWindow {

    public MainWindow(App app) { super(app, "openViolet"); }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox();
        actions.addItem("Login", () -> { app.push(new LoginWindow(app));});
        actions.addItem("Register", () -> { app.push(new RegisterWindow(app)); });
        actions.addItem("Exit", () -> { app.exit(); });

        panel.addComponent(actions);

    }

}
