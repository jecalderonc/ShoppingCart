package com.service.shoppingcart.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.shoppingcart.model.dao.CartDAO;
import com.service.shoppingcart.model.dao.ItemCartDAO;
import com.service.shoppingcart.service.ShoppingCartService;
import com.service.shoppingcart.service.ShoppingCartServiceImpl;

/*
 * Test Config for classes in the package com.service.shoppingcart.service
 * 
 * @author je.calderon
 *
 */
@Configuration
public class TestConfig {
	
    @MockBean
    private CartDAO cartDao;
    
    @MockBean
    private ItemCartDAO itemCartDao;

    @Bean
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartServiceImpl();
    }
}
