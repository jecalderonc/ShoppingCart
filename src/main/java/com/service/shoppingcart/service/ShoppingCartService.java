package com.service.shoppingcart.service;

import java.io.IOException;
import java.util.List;

import com.service.shoppingcart.entities.Cart;
import com.service.shoppingcart.entities.ItemCart;
import com.service.shoppingcart.externalservice.Item;

/*
 * Interface ShoppingCartService that provides the logic of the shopping car
 */
public interface ShoppingCartService {
	
    List<Item> getArticles() throws IOException;

    Item getArticlesById(int id) throws IOException;

    Cart createCart(ItemCart itemCart);

    Cart addItemCart(int cartId, ItemCart itemCart);

    Cart removeItemCart(int casrtId, int itemId);

    double getTotalAmount(int casrtId);
}
