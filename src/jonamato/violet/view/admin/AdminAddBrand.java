package jonamato.violet.view.admin;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class AdminAddBrand extends AppWindow {

    public AdminAddBrand(App app) { super(app, "Add Brand"); }

    @Override
    protected void init(Panel panel) {

        TextBox box = new TextBox();
        Button button = new Button("Add", () -> {

            if(box.getText().isEmpty()) { return; }

            Brands.instance().add(new Brand().setName(box.getText()));
            app.pop();

        });

        panel.addComponent(box);
        panel.addComponent(button);

    }

}
