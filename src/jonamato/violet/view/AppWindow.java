package jonamato.violet.view;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import jonamato.violet.account.User;

import java.util.Arrays;

public abstract class AppWindow extends BasicWindow {

    protected App app;
    protected Panel panel;
    protected User user;

    public AppWindow(App app, String title, User user) {

        super(title);
        this.app = app;
        this.user = user;

        setHints(Arrays.asList(Window.Hint.CENTERED));

    }

    protected abstract void init(Panel panel);

    public AppWindow build() {

        panel = new Panel();
        init(panel);
        setComponent(panel);
        return this;

    }

}
