package jonamato.violet.view.product;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.account.User;
import jonamato.violet.product.Orders;
import jonamato.violet.product.Product;
import jonamato.violet.product.ProductStack;
import jonamato.violet.product.Products;
import jonamato.violet.store.Store;
import jonamato.violet.store.Stores;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

import java.util.regex.Pattern;

public class ProductViewWindow extends AppWindow {

    private Store store;
    private ProductStack stack;

    public ProductViewWindow(App app, User user, Store store, ProductStack stack) {

        super(app, stack.getProductName(), user);
        this.store = store;
        this.stack = stack;

    }

    @Override
    protected void init(Panel panel) {

        Product product = Products.instance().all().stream()
                .filter(p -> p.getID().equals(stack.getProductID()))
                .findFirst()
                .orElse(new Product().setName("Default?"));

        Stores.instance().view(store, stack, 1);

        panel.setLayoutManager(new GridLayout(2));

        Label nameLabel = new Label("Name");
        Label nameBox = new Label(product.getName());

        Label descLabel = new Label("Description");
        Label descBox = new Label(product.getDescription());

        Label brandLabel = new Label("Brand");
        Label brandBox = new Label(product.getBrand());

        Label categoryLabel = new Label("Category");
        Label categoryBox = new Label(product.getCategory());

        Label qtyLabel = new Label("Qty");
        Label qtyBox = new Label(String.valueOf(stack.getQuantity()));

        Label priceLabel = new Label("Price");
        Label priceBox = new Label(String.valueOf(stack.getPrice()));

        Label stackViewsLabel = new Label("Views in Store");
        Label stackViewsBox = new Label(String.valueOf(stack.views()));

        Label stackOrdersLabel = new Label("Orders in Store");
        Label stackOrdersBox = new Label(String.valueOf(stack.orders()));

        Label toBuyLabel = new Label("Qty Wanted");
        TextBox toBuyBox = new TextBox().setValidationPattern(Pattern.compile("^([1-9][0-9]*)$"));

        Button buy = new Button("Buy", () -> {

            if(toBuyBox.getText().isEmpty()) { return; }
            if(!Orders.instance().order((Buyer)user, store, stack, Integer.parseInt(toBuyBox.getText()))) { return; }

            app.pop();

        }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING));

        Button back = new Button("Back", app::pop);

        panel.addComponent(nameLabel);
        panel.addComponent(nameBox);
        panel.addComponent(descLabel);
        panel.addComponent(descBox);
        panel.addComponent(brandLabel);
        panel.addComponent(brandBox);
        panel.addComponent(categoryLabel);
        panel.addComponent(categoryBox);
        panel.addComponent(qtyLabel);
        panel.addComponent(qtyBox);
        panel.addComponent(priceLabel);
        panel.addComponent(priceBox);

        if(user instanceof Owner) {

            if(((Owner)user).isPremium()) {

                panel.addComponent(stackViewsLabel);
                panel.addComponent(stackViewsBox);
                panel.addComponent(stackOrdersLabel);
                panel.addComponent(stackOrdersBox);

            }

        }

        if(user instanceof Buyer) {

            panel.addComponent(toBuyLabel);
            panel.addComponent(toBuyBox);

            panel.addComponent(back);
            panel.addComponent(buy);

        } else { panel.addComponent(back); }

    }

}
