package com.service.shoppingcart.externalservice;

import lombok.Getter;
import lombok.Setter;

/*
 * DTAO Class to define the Item information retrieved in the Items Service
 */
@Getter
@Setter
public class Item {
    private String id;
    private String title;
    private String price;
}
