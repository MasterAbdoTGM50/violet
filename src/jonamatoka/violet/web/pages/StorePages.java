package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.product.Category;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.product.ProductStack;
import jonamatoka.violet.store.Store;
import jonamatoka.violet.store.Stores;
import jonamatoka.violet.util.NitriteHelper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        Stores.get().add(store);
        NitriteHelper.get().all(Store.class).forEach(System.out::println);

        return "redirect:" + Lib.Mappings.ADD_STORE_SYSTEM;
    }

    @RequestMapping(value = Lib.Mappings.VIEW_SYSTEM_STATISTICS, method = RequestMethod.GET)
    public String getViewStoreStatisticsToSystem(Model model, @RequestParam("id") String id) {

        List<Store> stores = Stores.get().all();

        if (stores.size() == 0) { stores.add(new Store()); }

        model.addAttribute("stores", stores);

        return Lib.Templates.VIEW_SYSTEM_STATISTICS;
    }

    @RequestMapping(value = Lib.Mappings.VIEW_SYSTEM_STORES, method = RequestMethod.GET)
    public String getViewSystemStores(Model model) {

        model.addAttribute("stores", Stores.get().all());

        return Lib.Templates.VIEW_SYSTEM_STORES;

    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE, method = RequestMethod.GET)
    public String getStorePage(Model model, @RequestParam("id") String id) {

        Store store = Stores.get().find(id);
        
        model.addAttribute("products", store.getInventory().get());

        return Lib.Templates.GET_STORE_PAGE;

    }

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_STORE, method = RequestMethod.GET)
    public String getAddProductToStore(Model model) {

        model.addAttribute("productStack", new ProductStack());
        model.addAttribute("productList", NitriteHelper.get().all(Product.class));
        model.addAttribute("selectedProduct", new Product());

        return Lib.Templates.ADD_PRODUCT_STORE;

    }

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_STORE, method = RequestMethod.POST)
    public String postAddProductToStore(@ModelAttribute("productStack") ProductStack productStack, @ModelAttribute("selectedProduct") Product product) {

       // ToDo: Add Product to Store logic

        return "redirect:" + Lib.Mappings.ADD_PRODUCT_STORE;
    }
}