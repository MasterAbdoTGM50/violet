package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.product.ProductStack;
import jonamatoka.violet.store.Store;
import jonamatoka.violet.store.Stores;
import jonamatoka.violet.util.NitriteHelper;

import jonamatoka.violet.util.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE + "/{storeId}", method = RequestMethod.GET)
    public String getStorePage(Model model, @PathVariable("storeId") String storeId) {

        Store store = Stores.get().find(storeId);

        model.addAttribute("storeId", store.getId());
        model.addAttribute("products", store.getInventory().get());

        return Lib.Templates.GET_STORE_PAGE;

    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE + "/{storeId}/{productId}", method = RequestMethod.GET)
    public String getStoreBuyProductPage(Model model, @PathVariable("storeId") String storeId, @PathVariable("productId") String productId)  {

        Store store = Stores.get().find(storeId);

        ProductStack product = store.getInventory().get().stream().filter(ps -> ps.getProductId().equals(productId)).findFirst().get();

        Order order = new Order(storeId, productId);

        model.addAttribute("storeId", store.getId());
        model.addAttribute("product", product);
        model.addAttribute("order", order);

        return Lib.Templates.GET_STORE_PAGE;
    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE + "/{storeId}/{productId}", method = RequestMethod.POST)
    public String getStoreBuyProductPage(@ModelAttribute("order") Order order) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ProductStack product = Stores.get().find(order.getStoreId()).getInventory().get().stream().filter(ps -> ps.getProductId().equals(order.getProductId())).findFirst().get();

        product.setQuantity(product.getQuantity() - order.getQuantity());

        user.getWallet().deduct(product.getPrice() * order.getQuantity());

        user.getCart().add(product);

        return "redirect:" + Lib.Mappings.GET_STORE_PAGE + "/" + order.getStoreId();
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