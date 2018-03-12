package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.util.NitriteHelper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BrandPages {

    @RequestMapping(value = Lib.Mappings.ADD_BRAND_SYSTEM, method = RequestMethod.GET)
    public String getAddBrandToSystem(Model model) {

        model.addAttribute("brand", new Brand());

        return Lib.Templates.ADD_BRAND_SYSTEM;

    }

    @RequestMapping(value = Lib.Mappings.ADD_BRAND_SYSTEM, method = RequestMethod.POST)
    public String postAddBrandToSystem(@ModelAttribute("brand") Brand brand) {

        NitriteHelper.get().insert(brand, Brand.class);

        return "redirect:" + Lib.Mappings.ROOT;

    }

}
