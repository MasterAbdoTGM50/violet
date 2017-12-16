package jonamato.violet;

import jonamato.violet.view.App;
import jonamato.violet.view.MainWindow;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        App app = new App();
        app.start(new MainWindow(app));

    }

}
