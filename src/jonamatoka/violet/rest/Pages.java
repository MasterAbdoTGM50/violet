package jonamatoka.violet.rest;

import jonamatoka.violet.Platform;
import jonamatoka.violet.account.User;
import jonamatoka.violet.util.StringContainer;
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
    public String register(Model model) {

        model.addAttribute("user", new User());
        return "register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute("user") User user, @RequestParam("pass") String pass) {

        Platform.get().db.getRepository(User.class)
                .insert(user.setHash(LongHashFunction.xx().hashChars(pass)).setPriviliges(0));
        return "redirect:/login";

    }



}
