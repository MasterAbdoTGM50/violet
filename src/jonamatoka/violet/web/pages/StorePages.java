package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.store.Store;
import jonamatoka.violet.util.NitriteHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = Lib.Mappings.VIEW_STATISTICS_SYSTEM , method = RequestMethod.GET)
    public String getViewStoreStatisticsToSystem(Model model, @RequestParam("id") String id) {

        List<Store> stores = NitriteHelper.get()
                                        .all(Store.class)
                                        .stream()
                                        .filter(s -> id.equals(s.getId()))
                                        .collect(Collectors.toList());

        if(stores.size() == 0) { stores.add(new Store()); }
        model.addAttribute("stores", stores);

        return "vsts";
    }
}