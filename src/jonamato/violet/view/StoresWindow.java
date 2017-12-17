package jonamato.violet.view;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.User;
import jonamato.violet.store.Stores;
import jonamato.violet.view.store.StoreWindow;

public class StoresWindow extends AppWindow {

    public StoresWindow(App app, User user) { super(app, "Stores", user); }

    @Override
    protected void init(Panel panel) {

        ActionListBox actions = new ActionListBox();
        Stores.instance().all().forEach(s -> {

            actions.addItem("View " + s.getName(), () -> { app.push(new StoreWindow(app, user, s).build());});

        });

        Button back = new Button("Back", app::pop);

        panel.addComponent(actions);
        panel.addComponent(back);

    }

}
