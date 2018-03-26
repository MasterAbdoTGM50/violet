package jonamatoka.violet.web.pages;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.product.ProductStack;
import jonamatoka.violet.store.Store;

import jonamatoka.violet.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StorePages {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = Lib.Mappings.ADD_STORE_SYSTEM, method = RequestMethod.GET)
    public String getAddStoreToSystem(Model model) {

        model.addAttribute("store", new Store());

        return Lib.Templates.ADD_STORE_SYSTEM;

    }

    @RequestMapping(value = Lib.Mappings.ADD_STORE_SYSTEM, method = RequestMethod.POST)
    public String postAddStoreToSystem(@ModelAttribute("store") Store store) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        store.setOwnerId(user.getUsername());

        storeRepository.save(store);

        return "redirect:" + Lib.Mappings.ADD_STORE_SYSTEM;
    }

    @RequestMapping(value = Lib.Mappings.VIEW_SYSTEM_STATISTICS, method = RequestMethod.GET)
    public String getViewStoreStatisticsToSystem(Model model, @RequestParam("id") String id) {

        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);

        if (stores.size() == 0) { stores.add(new Store()); }

        model.addAttribute("stores", stores);

        return Lib.Templates.VIEW_SYSTEM_STATISTICS;
    }

    @RequestMapping(value = Lib.Mappings.VIEW_SYSTEM_STORES, method = RequestMethod.GET)
    public String getViewSystemStores(Model model) {

        model.addAttribute("stores", storeRepository.findAll());

        return Lib.Templates.VIEW_SYSTEM_STORES;

    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE + "/{storeId}", method = RequestMethod.GET)
    public String getStorePage(Model model, @PathVariable("storeId") Long storeId) {

        Store store = storeRepository.findOne(storeId);

        model.addAttribute("storeId", store.getId());
        model.addAttribute("products", store.getInventory().get());

        return Lib.Templates.GET_STORE_PAGE;

    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE + "/{storeId}/{productId}", method = RequestMethod.GET)
    public String getStoreBuyProductPage(Model model, @PathVariable("storeId") Long storeId, @PathVariable("productId") Long productId)  {

        Store store = storeRepository.findOne(storeId);

        ProductStack product = store.getInventory().get().stream().filter(ps -> ps.getProductId() == productId).findFirst().get();

        Order order = new Order(storeId, productId);

        model.addAttribute("storeId", store.getId());
        model.addAttribute("product", product);
        model.addAttribute("order", order);

        return Lib.Templates.GET_STORE_PAGE;
    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_PAGE + "/{storeId}/{productId}", method = RequestMethod.POST)
    public String getStoreBuyProductPage(@ModelAttribute("order") Order order) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ProductStack product = storeRepository.findOne(order.getStoreId()).getInventory().get().stream().filter(ps -> ps.getProductId() == order.getProductId()).findFirst().get();

        product.setQuantity(product.getQuantity() - order.getQuantity());

        user.getWallet().deduct(product.getPrice() * order.getQuantity());

        user.getCart().add(product);

        return "redirect:" + Lib.Mappings.GET_STORE_PAGE + "/" + order.getStoreId();
    }


    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_STORE, method = RequestMethod.GET)
    public String getAddProductToStore(Model model) {

        model.addAttribute("productStack", new ProductStack());
        model.addAttribute("productList", productRepository.findAll());
        model.addAttribute("selectedProduct", new Product());

        return Lib.Templates.ADD_PRODUCT_STORE;

    }

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_STORE, method = RequestMethod.POST)
    public String postAddProductToStore(@ModelAttribute("productStack") ProductStack productStack, @ModelAttribute("selectedProduct") Product product) {

       // TODO: Add Product to Store logic

        return "redirect:" + Lib.Mappings.ADD_PRODUCT_STORE;
    }
}