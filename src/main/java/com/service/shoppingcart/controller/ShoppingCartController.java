package com.service.shoppingcart.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.shoppingcart.entities.Cart;
import com.service.shoppingcart.entities.ItemCart;
import com.service.shoppingcart.externalservice.Item;
import com.service.shoppingcart.service.ShoppingCartService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * ShoppingCart Controller
 * 
 * @author je.calderon
 *
 */
@RestController
@RequestMapping("/cartshopping")
public class ShoppingCartController {

	private static final String SERVICE_METHOD = "ServiceMethod";

	@Autowired
	ShoppingCartService shoppingCartService;

	@ApiOperation(value = "Retrieve the articles information", notes = "", response = List.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to get fata from http://challenge.getsandbox.com", 
			response = Void.class) })
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public List<Item> getArticles() throws IOException {
		return shoppingCartService.getArticles();
	}

	@ApiOperation(value = "Retrieve the article detail by Id", notes = "", response = Item.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to get fata from http://challenge.getsandbox.com", 
			response = Void.class) })
	@RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
	public Item getArticlesById(
			@ApiParam(value = "Id of the Article", required = true)  @PathVariable("id") int id) throws IOException {
		return shoppingCartService.getArticlesById(id);
	}

	@ApiOperation(value = "Create a new Shopping car sending one new item", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed creating the car", 
			response = Void.class) })
	@RequestMapping(value = "/cart/create", method = RequestMethod.POST)
	public Cart createCart(@ApiParam(value = "Item to create the new Shopping Car", required = true) @RequestBody ItemCart itemCart) {
		return shoppingCartService.createCart(itemCart);
	}

	@ApiOperation(value = "Add a new item to a existing Car", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to add add the item", 
			response = Void.class) })
	@RequestMapping(value = "/cart/add/", method = RequestMethod.POST)
	public Cart addItemCart(@ApiParam(value = "Item to add at the existing car", required = true) @RequestBody ItemCart itemCart, @ApiParam(value = "Id of the Car to add the item", required = true) @RequestBody int cartId) {
		return shoppingCartService.addItemCart(cartId, itemCart);
	}

	@ApiOperation(value = "Remove an especific item from existing Car", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to remove the item", 
			response = Void.class) })
	@RequestMapping(value = "/cart/remove/", method = RequestMethod.PUT)
	public Cart removeItemCart(@ApiParam(value =  "Id of the Car to remove the item", required = true) @RequestBody int cartId, @ApiParam(value = "Id of the Item to removem", required = true) @RequestBody int itemId) {
		return shoppingCartService.removeItemCart(cartId, itemId);
	}

	@ApiOperation(value = "Retrieve the total amount of the car", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to obtain the amount", 
			response = Void.class) })
	@RequestMapping(value = "/cart/getTotal/{id}", method = RequestMethod.GET)
	public double getTotalAmount(@ApiParam(value =  "Id of the Car to consult", required = true) @PathVariable("id") int id) {
		return shoppingCartService.getTotalAmount(id);
	}
}
