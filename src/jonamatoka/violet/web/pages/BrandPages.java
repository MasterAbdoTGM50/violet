package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BrandPages {

    @Autowired
    private BrandRepository brandRepository;

    @RequestMapping(value = Lib.Mappings.ADD_BRAND_SYSTEM, method = RequestMethod.GET)
    public String getAddBrandToSystem(Model model) {

        model.addAttribute("brand", new Brand());

        return Lib.Templates.ADD_BRAND_SYSTEM;

    }

    @RequestMapping(value = Lib.Mappings.ADD_BRAND_SYSTEM, method = RequestMethod.POST)
    public String postAddBrandToSystem(@ModelAttribute("brand") Brand brand) {

        brandRepository.save(brand);

        return "redirect:" + Lib.Mappings.ROOT;

    }

}
