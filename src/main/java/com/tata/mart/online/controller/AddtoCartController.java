package com.tata.mart.online.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tata.mart.online.JWTConfiguration.ShoppingConfiguration;
import com.tata.mart.online.controller.RequestPojo.ApiResponse;
import com.tata.mart.online.model.AddtoCart;
import com.tata.mart.online.service.CartService.CartService;

@RestController
@RequestMapping("api/addtocart")
public class AddtoCartController {
	
	@Autowired
	CartService cartService;
	@RequestMapping("addProduct")
	public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> addCartRequest){
		try {
			String keys[] = {"productId","userId","qty","price"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
				long productId = Long.parseLong(addCartRequest.get("productId"));
				long userId = Long.parseLong(addCartRequest.get("userId"));
				int qty = Integer.parseInt(addCartRequest.get("qty"));
				double price = Double.parseDouble(addCartRequest.get("price"));
				List<AddtoCart> obj = cartService.addCartbyUserIdAndProductId(productId,userId,qty,price);
				
				return ResponseEntity.ok(obj);
			}else {
				throw new Exception("Error in passing Parameters");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
	
	
	@RequestMapping("updateQtyForCart")
	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest){
		try {
			String keys[] = {"cartId","userId","qty","price"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
				long cartId = Long.parseLong(addCartRequest.get("cartId"));
				long userId = Long.parseLong(addCartRequest.get("userId"));
				int qty =  Integer.parseInt(addCartRequest.get("qty")); 
				double price = Double.parseDouble(addCartRequest.get("price"));
				cartService.updateQtyByCartId(cartId, qty, price);
				List<AddtoCart> obj = cartService.getCartByUserId(userId);
				return ResponseEntity.ok(obj);
			}else {
				throw new Exception("Error in passing Parameter");
			}
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(),""));
		}
	}
	
	
	
	
	@RequestMapping("removeProductFromCart")
	public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest){
		try {
			String keys[] = {"cartId","userId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

				long cartId = Long.parseLong(removeCartRequest.get("cartId"));
				long userId = Long.parseLong(removeCartRequest.get("userId"));
				List<AddtoCart> obj = cartService.removeCartByUserId(cartId,userId);
			
			return ResponseEntity.ok(obj);
			}else {
				throw new Exception("Error in passing Parameters");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
	
	
	
	
	@RequestMapping("getCartsByUserId")
	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest){
		try {
			String keys[] = {"userId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
				
				long userId = Long.parseLong(getCartRequest.get("userId"));
				List<AddtoCart> obj = cartService.getCartByUserId(userId);
				
				return ResponseEntity.ok(obj);
			}else {
				throw new Exception("Error in passing Parameters");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
	
	
	
	
}
