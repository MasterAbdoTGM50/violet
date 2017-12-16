package jonamato.violet.view.admin;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class AdminRemoveBrand extends AppWindow {

    public AdminRemoveBrand(App app) { super(app, "Remove Brand"); }

    @Override
    protected void init(Panel panel) {

        CheckBoxList<Brand> checkBox = new CheckBoxList<>(new TerminalSize(20, 10));
        Brands.instance().all().forEach(checkBox::addItem);

        Button button = new Button("Remove", () -> {

            checkBox.getCheckedItems().forEach(Brands.instance()::remove);
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(checkBox);
        panel.addComponent(button);
        panel.addComponent(cancel);

    }

}
