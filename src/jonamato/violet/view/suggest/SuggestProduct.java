package jonamato.violet.view.suggest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import jonamato.violet.suggestion.ProductSuggestion;
import jonamato.violet.suggestion.SuggestedProducts;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class SuggestProduct extends AppWindow {

    public SuggestProduct(App app) { super(app, "Suggest Product"); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label nameLabel = new Label("Name");
        TextBox nameBox = new TextBox();

        Label brandLabel = new Label("Brand");
        Label categoryLabel = new Label("Category");

        RadioBoxList<Brand> brandRadio = new RadioBoxList<>(new TerminalSize(20, 10));
        Brands.instance().all().forEach(brandRadio::addItem);

        RadioBoxList<Category> categoryRadio = new RadioBoxList<>(new TerminalSize(20, 10));
        Categories.instance().all().forEach(categoryRadio::addItem);

        Button cancel = new Button("Cancel", app::pop);

        Button suggest = new Button("Suggest", () -> {

            if(nameBox.getText().isEmpty()) { return; }
            if(brandRadio.getCheckedItem() == null) { return; }
            if(categoryRadio.getCheckedItem() == null) { return; }

            ProductSuggestion product =
                    new ProductSuggestion()
                            .setName(nameBox.getText())
                            .setBrand(brandRadio.getCheckedItem().getName())
                            .setCategory(categoryRadio.getCheckedItem().getName())
                            .genID();

            if(!SuggestedProducts.instance().suggest(product)) { return; }

            app.pop();

        }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING));

        panel.addComponent(nameLabel);
        panel.addComponent(nameBox);
        panel.addComponent(brandLabel);
        panel.addComponent(categoryLabel);
        panel.addComponent(brandRadio);
        panel.addComponent(categoryRadio);
        panel.addComponent(cancel);
        panel.addComponent(suggest);

    }

}
