package jonamato.violet.view.suggest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.Panel;
import jonamato.violet.suggestion.BrandSuggestion;
import jonamato.violet.suggestion.BrandSuggestions;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class RejectBrand extends AppWindow {

    public RejectBrand(App app) { super(app, "Suggested Brands", null); }

    @Override
    protected void init(Panel panel) {

        CheckBoxList<BrandSuggestion> checkBox = new CheckBoxList<>(new TerminalSize(20, 10));
        BrandSuggestions.instance().all().forEach(checkBox::addItem);

        Button button = new Button("Reject", () -> {

            checkBox.getCheckedItems().forEach(BrandSuggestions.instance()::remove);
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(checkBox);
        panel.addComponent(button);
        panel.addComponent(cancel);


    }

}
