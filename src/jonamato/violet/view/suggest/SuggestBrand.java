package jonamato.violet.view.suggest;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import jonamato.violet.suggestion.BrandSuggestion;
import jonamato.violet.suggestion.BrandSuggestions;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class SuggestBrand extends AppWindow {

    public SuggestBrand(App app) { super(app, "Suggest Brand"); }

    @Override
    protected void init(Panel panel) {

        TextBox box = new TextBox();
        Button button = new Button("Suggest", () -> {

            if(box.getText().isEmpty()) { return; }

            BrandSuggestions.instance().suggest(new BrandSuggestion().setName(box.getText()));
            app.pop();

        });

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(box);
        panel.addComponent(button);
        panel.addComponent(cancel);

    }

}
