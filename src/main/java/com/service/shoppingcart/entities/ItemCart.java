package com.service.shoppingcart.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * ItemCart Entity
 * 
 * @author je.calderon
 *
 */
@Getter
@Setter
public class ItemCart {
    private int itemId;
    private int itemTittle;
    private int itemPrice;
    private int quantity;

}
