package jonamato.violet.view;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import java.util.Arrays;

public abstract class AppWindow extends BasicWindow {

    protected App app;

    public AppWindow(App app, String title) {

        super(title);
        this.app = app;

        setHints(Arrays.asList(Window.Hint.CENTERED));

        Panel panel = new Panel();
        init(panel);

        setComponent(panel);

    }

    protected abstract void init(Panel panel);

}
