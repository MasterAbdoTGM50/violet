package jonamato.violet.view.owner;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.account.Owner;
import jonamato.violet.account.User;
import jonamato.violet.store.OnlineStore;
import jonamato.violet.store.OnsiteStore;
import jonamato.violet.store.Store;
import jonamato.violet.store.Stores;
import jonamato.violet.util.Address;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

public class OwnerAddStore extends AppWindow {

    public OwnerAddStore(App app, User user) { super(app, "Add Store", user); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label nameLabel = new Label("Name");
        TextBox nameBox = new TextBox();

        Label countryLabel = new Label("Country");
        TextBox countryBox = new TextBox();

        Label governateLabel = new Label("Governate");
        TextBox governarteBox = new TextBox();

        Label streetLabel = new Label("Street");
        TextBox streetBox = new TextBox();

        Label typesLabel = new Label("Type");
        RadioBoxList<String> typesBox = new RadioBoxList<>();
        typesBox.addItem(OnlineStore.class.getSimpleName());
        typesBox.addItem(OnsiteStore.class.getSimpleName());

        typesBox.addListener((selectedIndex, previousSelection) -> {

            if(typesBox.getItemAt(selectedIndex).equals(OnlineStore.class.getSimpleName())) {

                countryBox.setEnabled(false);
                governarteBox.setEnabled(false);
                streetBox.setEnabled(false);

            } else {

                countryBox.setEnabled(true);
                governarteBox.setEnabled(true);
                streetBox.setEnabled(true);

            }

        });

        Button add = new Button("Add", () -> {

            if(nameBox.getText().isEmpty()) { return; }

            Store store;
            if(typesBox.getCheckedItem().equals(OnlineStore.class.getSimpleName())) {

                store = new OnlineStore().setName(nameBox.getText());

            } else {

                if(countryBox.getText().isEmpty()) { return; }
                if(governarteBox.getText().isEmpty()) { return; }
                if(streetBox.getText().isEmpty()) { return; }

                store =
                        new OnsiteStore().setName(nameBox.getText())
                        .setAddress(
                                new Address()
                                .setCountry(countryBox.getText())
                                .setGovernate(governarteBox.getText())
                                .setStreet(streetBox.getText())
                        );

            }

            if(!Stores.instance().add((Owner)user, store)) { return; }

            app.pop();

        }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING));

        Button cancel = new Button("Cancel", app::pop);

        panel.addComponent(nameLabel);
        panel.addComponent(nameBox);
        panel.addComponent(countryLabel);
        panel.addComponent(countryBox);
        panel.addComponent(governateLabel);
        panel.addComponent(governarteBox);
        panel.addComponent(streetLabel);
        panel.addComponent(streetBox);
        panel.addComponent(typesLabel);
        panel.addComponent(typesBox);
        panel.addComponent(cancel);
        panel.addComponent(add);

    }

}
