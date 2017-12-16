package jonamato.violet.view.admin;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class AdminRemoveCategory extends AppWindow {

    public AdminRemoveCategory(App app) { super(app, "Remove Category"); }

    @Override
    protected void init(Panel panel) {

        CheckBoxList<Category> checkBox = new CheckBoxList<>(new TerminalSize(20, 10));
        Categories.instance().all().forEach(checkBox::addItem);

        Button button = new Button("Remove", () -> {

            checkBox.getCheckedItems().forEach(Categories.instance()::remove);
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(checkBox);
        panel.addComponent(button);
        panel.addComponent(cancel);

    }

}
