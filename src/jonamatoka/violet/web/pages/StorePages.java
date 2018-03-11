package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.store.Store;
import jonamatoka.violet.util.NitriteHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StorePages {

    @RequestMapping(value = Lib.Mappings.ADD_STORE_SYSTEM, method = RequestMethod.GET)
    public String getAddStoreToSystem(Model model) {
        model.addAttribute("store", new Store());
        return Lib.Templates.ADD_STORE_SYSTEM;
    }

    @RequestMapping(value = Lib.Mappings.ADD_STORE_SYSTEM, method = RequestMethod.POST)
    public String postAddStoreToSystem(@ModelAttribute("store") Store store) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        store.setOwnerId(user.getUsername());
        NitriteHelper.get().insert(store, Store.class);
        NitriteHelper.get().all(Store.class).forEach(System.out::println);
        return "redirect:" + Lib.Mappings.ADD_STORE_SYSTEM;
    }

}
