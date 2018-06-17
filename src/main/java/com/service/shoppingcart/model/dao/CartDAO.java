package com.service.shoppingcart.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.shoppingcart.model.entities.Cart;

@Repository
public interface CartDAO extends CrudRepository<Cart, Integer> {

}
