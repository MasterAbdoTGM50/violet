package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductPages {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_SYSTEM, method = RequestMethod.GET)
    public String getAddProductToSystem(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        return Lib.Templates.ADD_PRODUCT_SYSTEM;

    }

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_SYSTEM, method = RequestMethod.POST)
    public String postAddProductToSystem(@ModelAttribute("product") Product product) {

        productRepository.save(product);

        return "redirect:" + Lib.Mappings.ROOT;

    }

}
