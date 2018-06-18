package com.service.shoppingcart.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.shoppingcart.controller.ShoppingCartController;
import com.service.shoppingcart.service.ShoppingCartService;
/*
 * Test Config for classes in the package com.service.shoppingcart.controller
 * 
 * @author je.calderon
 *
 */
@Configuration
public class TestConfigResource {

	@MockBean
	private ShoppingCartService shoppingCartService;

	@Bean
	public ShoppingCartController shoppingCartController() {
		return new ShoppingCartController();
	}
}