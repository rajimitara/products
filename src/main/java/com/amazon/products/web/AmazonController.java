package com.amazon.products.web;

import com.amazon.products.dao.ProductDao;
import com.amazon.products.ds.AmazonProduct;
import com.amazon.products.ds.UpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/*   MVC Web Layer Controller to handle all incoming request from Base Product Page of the Application
 * */
@Controller
public class AmazonController {

    //TODO : Add login controller

    @Autowired
    public ProductDao productDao;

    // User is presented with all available products in inventory and view specific product details
    @GetMapping(path = "/api/products")
    public String getProducts(Model model, AmazonProduct product,@RequestParam(value="id",required=false) String id) {
            if(id==null) {
                model.addAttribute("productList", productDao.findAll());
                model.addAttribute("product", product);
                return "products";
            }else{
                Optional<AmazonProduct> amazonProductAvailable = productDao.findById(id.trim());
                if (amazonProductAvailable.isPresent())
                    model.addAttribute("product", amazonProductAvailable.get());
                else
                    model.addAttribute("product",product);
                return "checkProduct";
            }
    }


    //Valid Sellers can post new products into inventory and also update existing products.
    //Thymeleaf does not support PUT and hence did update of existing resource with Post mapping. But correct way is to use PUT

    @PostMapping(path = "/api/products")
    public String postProducts(@Valid @ModelAttribute AmazonProduct product, BindingResult bindingResult, Model model, @RequestParam("id") Optional<String> id) {
        if (id.isPresent()) {
            Optional<AmazonProduct> oldProduct = productDao.findById(id.get());
            if (oldProduct.isPresent()) {
                oldProduct.get().setCurrentPrice(product.getCurrentPrice());
                oldProduct.get().setTimestamp(product.getTimestamp());
                productDao.save(oldProduct.get());
            }
        } else {
            product.setId(UUID.randomUUID().toString());
            if (bindingResult.hasErrors()) {
                return getProducts(model, product,null);
            }
            productDao.save(product);
        }
        return "redirect:/api/products";

    }


    //Update existing products.
    //Thymeleaf does not support PUT and hence did update of existing resource with Post mapping. But correct way is to use PUT

    @PutMapping(path = "/api/product/")
    public String updateProduct(@RequestParam("id") String id, @RequestBody UpdateProduct product) {
        Optional<AmazonProduct> oldProduct = productDao.findById(id);
        if (oldProduct.isPresent()) {
            oldProduct.get().setCurrentPrice(product.getCurrentPrice());
            oldProduct.get().setTimestamp(product.getTimestamp());
            productDao.save(oldProduct.get());
        }
        return "redirect:/api/products";
    }
}
