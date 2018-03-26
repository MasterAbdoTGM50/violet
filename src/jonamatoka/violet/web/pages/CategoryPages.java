package jonamatoka.violet.web.pages;


import jonamatoka.violet.Lib;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.product.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryPages {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = Lib.Mappings.ADD_CATEGORY_SYSTEM, method = RequestMethod.GET)
    public String getAddCategoryToSystem(Model model) {

        model.addAttribute("category", new Category());

        return Lib.Templates.ADD_CATEGORY_SYSTEM;

    }

    @RequestMapping(value = Lib.Mappings.ADD_CATEGORY_SYSTEM, method = RequestMethod.POST)
    public String postAddCategoryToSystem(@ModelAttribute("category") Category category) {

        categoryRepository.save(category);

        return "redirect:" + Lib.Mappings.ROOT;

    }

}
