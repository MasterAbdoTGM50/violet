package jonamato.violet.view.owner;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import jonamato.violet.product.Product;
import jonamato.violet.product.ProductStack;
import jonamato.violet.product.Products;
import jonamato.violet.store.Store;
import jonamato.violet.store.Stores;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

import java.util.regex.Pattern;

public class OwnerAddProduct extends AppWindow {

    private Store store;

    public OwnerAddProduct(App app, Store store) {

        super(app, "Add Product", null);
        this.store = store;

    }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        RadioBoxList<Product> productsRadio = new RadioBoxList<Product>(new TerminalSize(24, 10))
                .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2));

        Products.instance().all().forEach(productsRadio::addItem);

        Label qtyLabel = new Label("Quantity");
        TextBox qtyBox = new TextBox().setValidationPattern(Pattern.compile("^([1-9][0-9]*)$"));

        Label priceLabel = new Label("Price");
        TextBox priceBox = new TextBox().setValidationPattern(Pattern.compile("^(([1-9]*)|(([1-9]*)\\.([0-9]*)))$"));

        Button cancel = new Button("Cancel", app::pop);

        Button add = new Button("Add", () -> {

            if(productsRadio.getCheckedItem() == null) { return; }
            if(qtyBox.getText().isEmpty()) { return; }
            if(priceBox.getText().isEmpty()) { return; }

            ProductStack stack =
                    new ProductStack()
                    .setProductID(productsRadio.getCheckedItem().getID())
                    .setProductName(productsRadio.getCheckedItem().getName())
                    .setQuantity(Integer.parseInt(qtyBox.getText()))
                    .setPrice(Double.parseDouble(priceBox.getText()));

            if(!Stores.instance().add(store, stack)) { return; }

            app.pop();

        }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING));

        panel.addComponent(productsRadio);
        panel.addComponent(qtyLabel);
        panel.addComponent(qtyBox);
        panel.addComponent(priceLabel);
        panel.addComponent(priceBox);
        panel.addComponent(cancel);
        panel.addComponent(add);

    }

}
