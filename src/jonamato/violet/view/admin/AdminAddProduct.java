package jonamato.violet.view.admin;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import jonamato.violet.product.Product;
import jonamato.violet.product.Products;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class AdminAddProduct extends AppWindow {

    public AdminAddProduct(App app) { super(app, "Add Product", null); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label idLabel = new Label("ID");
        TextBox idBox = new TextBox();

        Label nameLabel = new Label("Name");
        TextBox nameBox = new TextBox();

        Label descLabel = new Label("Description");
        TextBox descBox = new TextBox(new TerminalSize(20, 4), TextBox.Style.MULTI_LINE);

        Label brandLabel = new Label("Brand");
        Label categoryLabel = new Label("Category");

        RadioBoxList<Brand> brandRadio = new RadioBoxList<>(new TerminalSize(20, 10));
        Brands.instance().all().forEach(brandRadio::addItem);

        RadioBoxList<Category> categoryRadio = new RadioBoxList<>(new TerminalSize(20, 10));
        Categories.instance().all().forEach(categoryRadio::addItem);

        Button cancel = new Button("Cancel", app::pop);

        Button add = new Button("Add", () -> {

            if(idBox.getText().isEmpty()) { return; }
            if(nameBox.getText().isEmpty()) { return; }
            if(descBox.getText().isEmpty()) { return; }
            if(brandRadio.getCheckedItem() == null) { return; }
            if(categoryRadio.getCheckedItem() == null) { return; }

            Product product =
                    new Product()
                    .setID(idBox.getText())
                    .setName(nameBox.getText())
                    .setDescription(descBox.getText())
                    .setBrand(brandRadio.getCheckedItem().getName())
                    .setCategory(categoryRadio.getCheckedItem().getName());

            if(!Products.instance().add(product)) { return; }

            app.pop();

        }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING));

        panel.addComponent(idLabel);
        panel.addComponent(idBox);
        panel.addComponent(nameLabel);
        panel.addComponent(nameBox);
        panel.addComponent(descLabel);
        panel.addComponent(descBox);
        panel.addComponent(brandLabel);
        panel.addComponent(categoryLabel);
        panel.addComponent(brandRadio);
        panel.addComponent(categoryRadio);
        panel.addComponent(cancel);
        panel.addComponent(add);

    }

}
