package com.tata.mart.online.service.CartService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tata.mart.online.Repository.AddToCartRepo;
import com.tata.mart.online.model.AddtoCart;
import com.tata.mart.online.model.Products;
import com.tata.mart.online.service.ProductService.ProductServices;

public class CartServiceImpl implements CartService{
	
	@Autowired
	AddToCartRepo addCartRepo;
	
	@Autowired
	ProductServices proServices;
	
	
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

 
	@Override
	public List<AddtoCart> addCartbyUserIdAndProductId(long productId, long userId,int qty,double price) throws Exception{
		try {
			if(addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()) {
				throw new Exception("Product is Already Present");
			}
			AddtoCart obj = new AddtoCart();
			obj.setQty(qty);
			obj.setUser_id(userId);
	
			//Products pro = x;
			obj.setPrice(price);
			addCartRepo.save(obj);		
			return this.getCartByUserId(userId);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<AddtoCart> getCartByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddtoCart> removeCartByUserId(long cartId, long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
