package com.service.shoppingcart.service;

import java.io.IOException;
import java.util.List;

import com.service.shoppingcart.externalservice.Item;
import com.service.shoppingcart.model.entities.Cart;
import com.service.shoppingcart.model.entities.ItemCart;

/*
 * Interface ShoppingCartService that provides the logic of the shopping car
 */
public interface ShoppingCartService {
	
    List<Item> getArticles() throws IOException;

    Item getArticlesById(int id) throws IOException;

    Cart createCart(Cart Cart);

    void addItemCart(Cart cartId, ItemCart itemCart);

    void removeItemCart(Cart castId, int itemId);

    Cart getCartById(int cartId);
    
    void clearCartById(Cart cartId);
}
