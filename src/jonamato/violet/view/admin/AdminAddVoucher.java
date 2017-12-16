package jonamato.violet.view.admin;

import com.googlecode.lanterna.gui2.*;
import jonamato.violet.product.voucher.Voucher;
import jonamato.violet.product.voucher.Vouchers;
import jonamato.violet.view.App;
import jonamato.violet.view.AppWindow;

import java.util.regex.Pattern;

public class AdminAddVoucher extends AppWindow {


    public AdminAddVoucher(App app) { super(app, "Add Voucher", null); }

    @Override
    protected void init(Panel panel) {

        panel.setLayoutManager(new GridLayout(2));

        Label codeLabel = new Label("Code");
        TextBox codeBox = new TextBox();

        Label discLabel = new Label("Discount");
        TextBox discBox = new TextBox().setValidationPattern(Pattern.compile("^(([1-9]*)|(([1-9]*)\\.([0-9]*)))$"));

        Label usesLabel = new Label("Uses");
        TextBox usesBox = new TextBox().setValidationPattern(Pattern.compile("^([1-9][0-9]*)$"));

        Button cancel = new Button("Cancel", app::pop);

        Button add = new Button("Add", () -> {

            if(codeBox.getText().isEmpty()) { return; }
            if(discBox.getText().isEmpty()) { return; }
            if(usesBox.getText().isEmpty()) { return; }

            Voucher voucher =
                    new Voucher()
                    .setCode(codeBox.getText())
                    .setDiscount(Double.parseDouble(discBox.getText()))
                    .setUses(Integer.parseInt(usesBox.getText()));

            if(!Vouchers.instance().add(voucher)) { return; }

            app.pop();

        });

        panel.addComponent(codeLabel);
        panel.addComponent(codeBox);
        panel.addComponent(discLabel);
        panel.addComponent(discBox);
        panel.addComponent(usesLabel);
        panel.addComponent(usesBox);
        panel.addComponent(cancel);
        panel.addComponent(add);


    }

}
