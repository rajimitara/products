
package com.amazon.products;

import com.amazon.products.dao.ProductDao;
import com.amazon.products.web.AmazonController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(AmazonController.class)
public class ProductsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDao productDao;

    @WithMockUser("USER")
    @Test
    public void validateGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk()).
                andExpect(view().name("products"));
    }

    @WithMockUser("USER")
    @Test
    public void validateGetByIdProducts() throws Exception {
        mockMvc.perform(get("/api/products/").param("id","b0203c10-ac28-4ea5-97ad-72ceec72d15f").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/api/products"));
    }
}
