package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.store.Store;
import jonamatoka.violet.store.Stores;
import jonamatoka.violet.util.NitriteHelper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = Lib.Mappings.VIEW_SYSTEM_STATISTICS, method = RequestMethod.GET)
    public String getViewStoreStatisticsToSystem(Model model, @RequestParam("id") String id) {

        List<Store> stores = Stores.instance().all();

        if (stores.size() == 0) { stores.add(new Store()); }

        model.addAttribute("stores", stores);

        return Lib.Templates.VIEW_SYSTEM_STATISTICS;
    }

    @RequestMapping(value = Lib.Mappings.VIEW_SYSTEM_STORES, method = RequestMethod.GET)
    public String getViewSystemStores(Model model) {

        model.addAttribute("stores", Stores.instance().all());

        return Lib.Templates.VIEW_SYSTEM_STORES;

    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE, method = RequestMethod.GET)
    public String getStorePage(Model model, @RequestParam("id") String id) {

        return Lib.Templates.GET_STORE_PAGE;

    }
}