package com.service.shoppingcart.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.shoppingcart.config.TestConfigResource;
import com.service.shoppingcart.externalservice.Item;
import com.service.shoppingcart.service.ShoppingCartService;


/*
 * Test Class for class ShoppingCartController
 * 
 * @author je.calderon
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestConfigResource.class})
@TestPropertySource("/application.properties")
public class ShoppingCartControllerTest {
	
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartController shoppingCartController;

    
    @Test
    public void getArticlesByIdOK() throws IOException {
        when(shoppingCartService.getArticlesById(anyInt())).thenReturn(new Item());
        ResponseEntity<Item>  responseShoppingCart = shoppingCartController.getArticlesById(1);
        assertEquals(HttpStatus.OK, responseShoppingCart.getStatusCode());
    }
    
    @Test
    public void getArticlesByIdDontExists() throws IOException {
        when(shoppingCartService.getArticlesById(anyInt())).thenReturn(null);
        ResponseEntity<Item>  responseShoppingCart = shoppingCartController.getArticlesById(1);
        assertEquals(HttpStatus.NOT_FOUND, responseShoppingCart.getStatusCode());
    }

}
