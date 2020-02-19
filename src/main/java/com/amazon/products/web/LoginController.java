package com.amazon.products.web;

import com.amazon.products.ds.AmazonProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class LoginController {

    //TODO : chnage to inmemory and protect /api/product url with edit/update/add operation incase of guest user.

    @GetMapping(path = "/")
    public String getloginPage(Model model) {
      return "login";
    }

    //Authenticate seller and direct to products page.
    @PostMapping(path = "/")
    public String loginUser(@Valid @ModelAttribute AmazonProduct product, BindingResult bindingResult,
                               Model model, @RequestParam("name") String name, @RequestParam("id") String id) {
        if (id == null || name == null)
            return "redirect:/";

        if (id.equals("dummy") && name.equals("dummy")) {
            //model.addAttribute("name",name)
            return "product";
        }
        return "redirect:/";
    }
}
