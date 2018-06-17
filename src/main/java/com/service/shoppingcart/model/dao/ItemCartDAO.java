package com.service.shoppingcart.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.shoppingcart.model.entities.Cart;
import com.service.shoppingcart.model.entities.ItemCart;

@Repository
public interface ItemCartDAO extends CrudRepository<ItemCart, Integer> {
	ItemCart findByCartidAndItemid(Cart cartid, Integer itemId);
	
	@Transactional
	void deleteByCartid(Cart cartId);
}
