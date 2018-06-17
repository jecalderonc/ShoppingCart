package com.service.shoppingcart.controller;
import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.shoppingcart.externalservice.Item;
import com.service.shoppingcart.model.entities.Cart;
import com.service.shoppingcart.model.entities.ItemCart;
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
			@ApiResponse(code = 404, message = "Article don´t exists", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to get fata from http://challenge.getsandbox.com", 
			response = Void.class) })
	@RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
	public ResponseEntity<Item> getArticlesById(
			@ApiParam(value = "Id of the Article", required = true)  @PathVariable("id") int id) throws IOException {
		Item item = shoppingCartService.getArticlesById(id);
		if(item != null)
			return ResponseEntity.status(Response.SC_OK).body(item);
		else
			return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
	}

	@ApiOperation(value = "Create a new Shopping car sending one new item", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed creating the car", 
			response = Void.class) })
	@RequestMapping(value = "/cart/create", method = RequestMethod.POST)
	public ResponseEntity<Cart> createCart(@ApiParam(value = "Item to create the new Shopping Car", required = true) @RequestBody Cart cart) {
		shoppingCartService.createCart(cart);
		return ResponseEntity.status(Response.SC_OK).body(cart);
	}

	@ApiOperation(value = "Add a new item to a existing Car", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 404, message = "Cart don´t exists in the DataBAse", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to add add the item", 
			response = Void.class) })
	@RequestMapping(value = "/cart/add/{cartId}", method = RequestMethod.POST)
	public ResponseEntity<ItemCart> addItemCart(@ApiParam(value = "Item to add at the existing car", required = true) @RequestBody ItemCart itemCart, @ApiParam(value = "Id of the Car to add the item", required = true) @PathVariable int cartId) {
		try {
			Cart cartItem = shoppingCartService.getCartById(cartId);
			if (cartItem != null) {
				Item item= shoppingCartService.getArticlesById(itemCart.getItemid());
				itemCart.setCartid(cartItem);
				itemCart.setItemprice(Double.valueOf(item.getPrice()));
				itemCart.setItemtitle(item.getTitle());
				shoppingCartService.addItemCart(cartItem, itemCart);
				return ResponseEntity.status(Response.SC_OK).body(itemCart);
			}
			else
			{
				return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Remove an especific item from existing Car", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 404, message = "Cart don´t exists in the DataBAse", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to remove the item", 
			response = Void.class) })
	@RequestMapping(value = "/cart/remove/{cartId}", method = RequestMethod.PUT)
	public ResponseEntity<String> removeItemCart(@ApiParam(value =  "Id of the Car to remove the item", required = true) @PathVariable int cartId,
			@ApiParam(value = "Id of the Item to removem", required = true) @RequestParam(required = true,  value="itemid") int itemId) {
		Cart cartItem = shoppingCartService.getCartById(cartId);
		if (cartItem != null) {
			shoppingCartService.removeItemCart(cartItem, itemId);
			return ResponseEntity.status(Response.SC_OK).body("OK");
		}
		else
		{
			return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
		}
	}

	@ApiOperation(value = "Retrieve the total amount of the car", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 404, message = "Cart don´t exists in the DataBAse", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to obtain the amount", response = Void.class) })
	@RequestMapping(value = "/cart/getTotal/{id}", method = RequestMethod.GET)
	public ResponseEntity<Double> getTotalAmount(@ApiParam(value =  "Id of the Car to consult", required = true) @PathVariable("id") int id) {
		Cart cart = shoppingCartService.getCartById(id);
		if(cart != null)
			return ResponseEntity.status(Response.SC_OK).body(cart.getTotalAmount());
		else
			return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
	}
	
	@ApiOperation(value = "Retrieve the cart details", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 404, message = "Cart don´t exists in the DataBAse", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to obtain the amount", response = Void.class) })
	@RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCart(@ApiParam(value =  "Id of the Car to consult", required = true) @PathVariable("id") int id) {
		Cart cart = shoppingCartService.getCartById(id);
		if(cart != null)
			return ResponseEntity.status(Response.SC_OK).body(cart);
		else
			return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
	}
	
	@ApiOperation(value = "Delete all the articles in the specific cart", notes = "", response = Cart.class
			, tags = { ShoppingCartController.SERVICE_METHOD })
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successful operation", response = Void.class),
			@ApiResponse(code = 404, message = "Cart don´t exists in the DataBAse", response = Void.class),
			@ApiResponse(code = 500, message = "Internal Server Error, Failed to obtain the amount", response = Void.class) })
	@RequestMapping(value = "/cart/clear/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cart> clearCart(@ApiParam(value =  "Id of the Car to clear", required = true) @PathVariable("id") int id) {
		Cart cart = shoppingCartService.getCartById(id);
		if(cart != null) {
			shoppingCartService.clearCartById(cart);
			cart = shoppingCartService.getCartById(id);
			return ResponseEntity.status(Response.SC_OK).body(cart);
		}else
			return ResponseEntity.status(Response.SC_NOT_FOUND).body(null);
	}
}
