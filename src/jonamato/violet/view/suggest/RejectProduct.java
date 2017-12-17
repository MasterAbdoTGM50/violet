package jonamato.violet.view.suggest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.suggestion.ProductSuggestion;
import jonamato.violet.suggestion.ProductSuggestions;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class RejectProduct extends AppWindow {

    public RejectProduct(App app) { super(app, "Suggested Products", null); }

    @Override
    protected void init(Panel panel) {

        CheckBoxList<ProductSuggestion> checkBox = new CheckBoxList<>(new TerminalSize(40, 10));
        ProductSuggestions.instance().all().forEach(checkBox::addItem);

        Button button = new Button("Reject", () -> {

            checkBox.getCheckedItems().forEach(ProductSuggestions.instance()::remove);
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(checkBox);
        panel.addComponent(button);
        panel.addComponent(cancel);

    }

}
