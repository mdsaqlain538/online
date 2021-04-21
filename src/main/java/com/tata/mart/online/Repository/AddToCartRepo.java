package com.tata.mart.online.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tata.mart.online.model.AddtoCart;

public interface AddToCartRepo extends JpaRepository<AddtoCart,Long>{
	
	
	@Query("Select sum(addCart.price) FROM AddtoCart addCart WHERE addCart.user_id=:user_id")
	double getTotalAmountByUserId(@Param("user_id")Long user_id);
	
	
	//getCartByuserId
	@Query("SELECT addCart FROM AddtoCart addCart WHERE addCart.user_id=:user_id")
	List<AddtoCart> getCartByuserId(@Param("user_id")Long user_id);
	
	//test query
	@Query("SELECT addCart FROM AddtoCart addCart")
	Optional<AddtoCart> getCartByuserIdtest();
	
	//getCartByProductIdAnduserId
	@Query("SELECT addCart FROM AddtoCart addCart WHERE addCart.user_id=:user_id and addCart.product.id=:product_id")
	Optional<AddtoCart> getCartByProductIdAnduserId(@Param("user_id")Long user_id,@Param("product_id")Long product_id);
	
	//deleteCartByIdAndUserId
	@Modifying
    @Transactional
	@Query("DELETE  FROM AddtoCart addCart WHERE addCart.id =:cart_id   and addCart.user_id=:user_id")
	void deleteCartByIdAndUserId(@Param("user_id")Long user_id,@Param("cart_id")Long cart_id);
	
	//deleteAllCartUserId
	@Modifying
	@Transactional
	@Query("DELETE  FROM AddtoCart addCart WHERE addCart.user_id=:user_id")
	void deleteAllCartUserId(@Param("user_id")Long user_id);
	
	//update
	@Modifying
    @Transactional
	@Query("update AddtoCart addCart set addCart.qty=:qty,addCart.price=:price WHERE addCart.id=:cart_id")
	void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("price")double price,@Param("qty")Integer qty);
	
}