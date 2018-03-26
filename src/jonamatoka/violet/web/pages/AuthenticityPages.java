package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;

import jonamatoka.violet.data.repo.UserRepository;
import net.openhft.hashing.LongHashFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticityPages {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = Lib.Mappings.LOGIN, method = RequestMethod.GET)
    public String login() { return Lib.Templates.LOGIN; }

    @RequestMapping(value = Lib.Mappings.REGISTER, method = RequestMethod.GET)
    public String getRegister(Model model) {

        model.addAttribute("user", new User());

        return Lib.Templates.REGISTER;

    }
    @RequestMapping(value = Lib.Mappings.REGISTER, method = RequestMethod.POST)
    public String postRegister(@ModelAttribute("user") User user, @RequestParam("pass") String pass) {

        userRepository.save(user.setHash(LongHashFunction.xx().hashChars(pass)).setPriviliges(6));

        return "redirect:" + Lib.Mappings.LOGIN;

    }

}
