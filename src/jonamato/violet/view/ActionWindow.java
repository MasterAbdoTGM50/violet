package jonamato.violet.view;

import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.account.User;

public class ActionWindow extends AppWindow {

    protected User activeUser;

    public ActionWindow(App app, User user) {

        super(app, user.getClass().getSimpleName() + ": " + user.getUsername() + " Actions");
        this.activeUser = user;
    }

    @Override
    protected void init(Panel panel) {

    }

}
