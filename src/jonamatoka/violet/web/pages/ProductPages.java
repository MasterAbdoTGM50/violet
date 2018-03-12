package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.product.Category;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.util.NitriteHelper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductPages {

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_SYSTEM, method = RequestMethod.GET)
    public String getAddProductToSystem(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("brands", NitriteHelper.get().all(Brand.class));
        model.addAttribute("categories", NitriteHelper.get().all(Category.class));

        return Lib.Templates.ADD_PRODUCT_SYSTEM;

    }

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_SYSTEM, method = RequestMethod.POST)
    public String postAddProductToSystem(@ModelAttribute("product") Product product) {

        NitriteHelper.get().insert(product, Product.class);
        NitriteHelper.get().all(Product.class).forEach(System.out::println);

        return "redirect:" + Lib.Mappings.ROOT;

    }

}
