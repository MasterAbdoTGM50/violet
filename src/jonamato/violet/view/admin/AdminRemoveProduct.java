package jonamato.violet.view.admin;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.product.Product;
import jonamato.violet.product.Products;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class AdminRemoveProduct extends AppWindow {

    public AdminRemoveProduct(App app) { super(app, "Remove Product", null); }

    @Override
    protected void init(Panel panel) {

        CheckBoxList<Product> checkBox = new CheckBoxList<>(new TerminalSize(20, 10));
        Products.instance().all().forEach(checkBox::addItem);

        Button button = new Button("Remove", () -> {

            checkBox.getCheckedItems().forEach(Products.instance()::remove);
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(checkBox);
        panel.addComponent(button);
        panel.addComponent(cancel);

    }

}
