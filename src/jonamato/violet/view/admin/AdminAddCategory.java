package jonamato.violet.view.admin;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class AdminAddCategory extends AppWindow {

    public AdminAddCategory(App app) { super(app, "Add Category", null); }

    @Override
    protected void init(Panel panel) {

        TextBox box = new TextBox();
        Button button = new Button("Add", () -> {

            if(box.getText().isEmpty()) { return; }

            Categories.instance().add(new Category().setName(box.getText()));
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(box);
        panel.addComponent(button);
        panel.addComponent(cancel);

    }

}
