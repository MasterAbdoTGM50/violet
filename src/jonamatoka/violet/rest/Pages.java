package jonamatoka.violet.rest;

import jonamatoka.violet.account.User;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.util.NitriteHelper;
import net.openhft.hashing.LongHashFunction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Pages {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("name", ((User)auth.getPrincipal()).getUsername());
        model.addAttribute("priv", auth.getAuthorities());
        return "index";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() { return "login"; }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {

        model.addAttribute("user", new User());
        return "register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute("user") User user, @RequestParam("pass") String pass) {

        NitriteHelper.insert(user.setHash(LongHashFunction.xx().hashChars(pass)).setPriviliges(6), User.class);
        return "redirect:/login";

    }

    @RequestMapping(value = "/apts", method = RequestMethod.GET)
    public String getAddProductToSystem(Model model) {

        model.addAttribute("product", new Product());
        return "apts";

    }

    @RequestMapping(value = "/apts", method = RequestMethod.POST)
    public String postAddProductToSystem(@ModelAttribute("product") Product product) {

        NitriteHelper.insert(product, Product.class);
        NitriteHelper.all(Product.class).forEach(System.out::println);

        return "redirect:/";

    }

}
