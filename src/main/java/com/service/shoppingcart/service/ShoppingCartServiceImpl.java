package com.service.shoppingcart.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shoppingcart.entities.Cart;
import com.service.shoppingcart.entities.ItemCart;
import com.service.shoppingcart.externalservice.IGetItems;
import com.service.shoppingcart.externalservice.Item;

import retrofit2.Response;

/*
 * Class with the implementation of the interface ShoppingCartService
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	IGetItems igetItems;
	
    public List<Item> getArticles() throws IOException{
    	Response<List<Item>> res = igetItems.getArticles().execute();
    	Optional<List<Item>> lst = Optional.ofNullable(res.body());
        return lst.get();
    }

    public Item getArticlesById(int id) throws IOException{
    	Response<Item> res = igetItems.getArticlesById(id).execute();
    	Optional<Item> lst = Optional.ofNullable(res.body());
        return lst.get();
    }

    public Cart createCart(ItemCart itemCart){
        return null;
    }

    public Cart addItemCart(int cartId, ItemCart itemCart){
        return null;
    }

    public Cart removeItemCart(int casrtId, int itemId){
        return null;
    }

    public double getTotalAmount(int casrtId){
        return 0;
    }
}
