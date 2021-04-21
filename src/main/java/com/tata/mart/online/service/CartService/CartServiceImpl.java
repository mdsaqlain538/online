package com.tata.mart.online.service.CartService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tata.mart.online.Repository.AddToCartRepo;
import com.tata.mart.online.Repository.CheckoutRepo;
import com.tata.mart.online.model.AddtoCart;
import com.tata.mart.online.model.CheckoutCart;
import com.tata.mart.online.model.Products;
import com.tata.mart.online.service.ProductService.ProductServices;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	AddToCartRepo addCartRepo;
	
	@Autowired
	ProductServices proServices;
	
	CheckoutRepo checkOutRepo;
	
	
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
			Products pro = proServices.getProductsById(productId);
			
			obj.setProduct(pro);

			obj.setPrice(price);
			addCartRepo.save(obj);		
			return this.getCartByUserId(userId);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	//saqlain_patel
	@Override
	public List<AddtoCart> getCartByUserId(long userId) {
		// TODO Auto-generated method stub
		return addCartRepo.getCartByuserId(userId);
	}

	@Override
	public List<AddtoCart> removeCartByUserId(long cartId, long userId) {
		// TODO Auto-generated method stub
		addCartRepo.deleteCartByIdAndUserId(userId, cartId);
		return this.getCartByUserId(userId);
	}

	@Override
	public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
		// TODO Auto-generated method stub
		addCartRepo.updateQtyByCartId(cartId,price,qty);

	}

	@Override
	public List<AddtoCart> removeAllCartByUserId(long userId) {
		// TODO Auto-generated method stub
		addCartRepo.deleteAllCartUserId(userId);
		return null;
	}

	@Override
	public Boolean checkTotalAmountAgainstCart(double totalAmount, long userId) {
		double total_amount =addCartRepo.getTotalAmountByUserId(userId);
		if(total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
		return false;
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
		return checkOutRepo.getByuserId(userId);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUser_id();
			if(tmp.size() >0) {
				checkOutRepo.saveAll(tmp);
				this.removeAllCartByUserId(user_id);
				return this.getAllCheckoutByUserId(user_id);
			}	
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout "+e.getMessage());
		}
	}
}
