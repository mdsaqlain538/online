package com.tata.mart.online.service.CartService;

import java.util.List;

import com.tata.mart.online.model.AddtoCart;

public interface CartService {
	List<AddtoCart> addCartbyUserIdAndProductId(long productId,long userId,int qty,double price) throws Exception;
	List<AddtoCart> getCartByUserId(long userId);
	List<AddtoCart> removeCartByUserId(long cartId,long userId);
	//checkOutCart
}