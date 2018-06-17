package com.service.shoppingcart.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shoppingcart.externalservice.IGetItems;
import com.service.shoppingcart.externalservice.Item;
import com.service.shoppingcart.model.dao.CartDAO;
import com.service.shoppingcart.model.dao.ItemCartDAO;
import com.service.shoppingcart.model.entities.Cart;
import com.service.shoppingcart.model.entities.ItemCart;

import retrofit2.Response;

/*
 * Class with the implementation of the interface ShoppingCartService
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	IGetItems igetItems;
	
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	ItemCartDAO itemCartDAO;
	
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

    public Cart createCart(Cart Cart){
        return cartDAO.save(Cart);
    }

    public void addItemCart(Cart cartId, ItemCart itemCart){
    	ItemCart idItem = itemCartDAO.findByCartidAndItemid(cartId, itemCart.getItemid());
    	if(idItem != null)
    		itemCart.setId(idItem.getId());
    	itemCartDAO.save(itemCart);
    }

    public void removeItemCart(Cart cartId, int itemId){

    	ItemCart idItem = itemCartDAO.findByCartidAndItemid(cartId, itemId);
    	if(idItem != null)
    		itemCartDAO.delete(idItem);
    }

    public Cart getCartById(int cartId){
    	return cartDAO.findOne(cartId);
    }
    
    public void clearCartById(Cart cartId){
    	itemCartDAO.deleteByCartid(cartId);
    }
}
