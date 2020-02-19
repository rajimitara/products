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

@Controller
public class ProductUpdateController {

    @Autowired
    public ProductDao productDao;

    @GetMapping(path = "/api/products/update/{id}")
    public String getProductByIdPage(Model model, @PathVariable("id") String id,@ModelAttribute AmazonProduct product) {
        model.addAttribute("product",productDao.findById(id));
        return "updateProduct";
    }

    @PostMapping(path = "/api/products/update")
    public String updateProducts(@ModelAttribute AmazonProduct product, BindingResult bindingResult, @RequestParam("id") String id) {
        Optional<AmazonProduct> oldProduct = productDao.findById(id);
        if (bindingResult.hasErrors()) {
            //return getProducts(model, product,null);
        }
        if (oldProduct.isPresent()) {
                oldProduct.get().setCurrentPrice(product.getCurrentPrice());
                oldProduct.get().setTimestamp(product.getTimestamp());
                productDao.save(oldProduct.get());
        }
        return "redirect:/api/products";
    }
}
