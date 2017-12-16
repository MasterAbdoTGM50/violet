package jonamato.violet.view.suggest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.product.brand.SuggestedBrand;
import jonamato.violet.product.brand.SuggestedBrands;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class RejectBrand extends AppWindow {

    public RejectBrand(App app) { super(app, "Suggested Brands"); }

    @Override
    protected void init(Panel panel) {

        CheckBoxList<SuggestedBrand> checkBox = new CheckBoxList<>(new TerminalSize(20, 10));
        SuggestedBrands.instance().all().forEach(checkBox::addItem);

        Button button = new Button("Reject", () -> {

            checkBox.getCheckedItems().forEach(Brands.instance()::remove);
            app.pop();

        });

        panel.addComponent(checkBox);
        panel.addComponent(button);


    }

}
