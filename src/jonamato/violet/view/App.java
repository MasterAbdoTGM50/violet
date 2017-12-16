package jonamato.violet.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Stack;

public class App {

    private Terminal terminal;
    private Screen screen;
    private MultiWindowTextGUI gui;
    private Stack<AppWindow> windows = new Stack<>();

    public void start(AppWindow window) {

        try {

            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));

            screen.startScreen();
            gui.addWindowAndWait(window);

        } catch (IOException e) { e.printStackTrace(); }

    }

    public void push(AppWindow window) {

        windows.push((AppWindow)gui.getActiveWindow());
        gui.removeWindow(gui.getActiveWindow());
        gui.addWindowAndWait(window);

    }

    public void pop() {

        gui.getActiveWindow().close();
        gui.addWindowAndWait(windows.pop());

    }

    public void exit() { try { terminal.close(); } catch (IOException e) { e.printStackTrace(); } }

}
