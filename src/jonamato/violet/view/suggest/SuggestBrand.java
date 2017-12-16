package jonamato.violet.view.suggest;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import jonamato.violet.product.brand.SuggestedBrand;
import jonamato.violet.product.brand.SuggestedBrands;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class SuggestBrand extends AppWindow {

    public SuggestBrand(App app) { super(app, "Suggest Brand"); }

    @Override
    protected void init(Panel panel) {

        TextBox box = new TextBox();
        Button button = new Button("Suggest", () -> {

            if(box.getText().isEmpty()) { return; }

            SuggestedBrands.instance().suggest(new jonamato.violet.product.brand.SuggestedBrand().setName(box.getText()));
            app.pop();

        });

        panel.addComponent(box);
        panel.addComponent(button);

    }

}
