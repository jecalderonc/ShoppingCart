package com.service.shoppingcart.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Cart Entity
 * 
 * @author je.calderon
 *
 */
@Getter
@Setter
public class Cart {
    private int id;
    private double price;
    private double totalPrice;
    private List<ItemCart> items;
}
