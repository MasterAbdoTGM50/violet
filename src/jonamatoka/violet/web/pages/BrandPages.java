package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.util.NitriteHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrandPages {
    @GetMapping(Lib.Mappings.ADD_Brand_SYSTEM)
    public String getAddBrandToSystem(Model model){
        model.addAttribute("brand",new Brand());
        return "abts";
    }
    @PostMapping(Lib.Mappings.ADD_Brand_SYSTEM)
    public String postAddBrandToSystem(@ModelAttribute("brand") Brand brand){
        NitriteHelper.get().insert(brand, Brand.class);
      //  NitriteHelper.get().all(Brand.class).forEach(System.out::println); //??
        return "redirect:" + Lib.Mappings.ROOT;
    }
}
