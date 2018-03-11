package jonamatoka.violet.web.pages;


import jonamatoka.violet.Lib;
import jonamatoka.violet.product.Category;
import jonamatoka.violet.util.NitriteHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryPages {
    @RequestMapping(value = Lib.Mappings.ADD_CATEGORY_SYSTEM, method = RequestMethod.GET)
    public String getAddCategoryToSystem(Model model) {
        model.addAttribute("category", new Category());
        return "acts";
    }

    @RequestMapping(value = Lib.Mappings.ADD_CATEGORY_SYSTEM, method = RequestMethod.POST)
    public String postAddCategoryToSystem(@ModelAttribute("category") Category category) {

        NitriteHelper.get().insert(category, Category.class);
        NitriteHelper.get().all(Category.class).forEach(System.out::println);
        return "redirect:" + Lib.Mappings.ROOT;
    }
}
