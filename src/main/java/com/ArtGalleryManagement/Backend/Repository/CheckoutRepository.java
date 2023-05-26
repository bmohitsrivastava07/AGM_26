package com.ArtGalleryManagement.Backend.Repository;

import com.ArtGalleryManagement.Backend.Entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	Logger logger=LoggerFactory.getLogger(CheckoutRepository.class);
    Checkout findByUserEmailAndProductId(String userEmail, Long productId);

    List<Checkout> findProductsByUserEmail(String userEmail);

    @Modifying
    @Query("delete from Checkout where product_id in :product_id")
    void deleteAllByProductId(@Param("product_id") Long productId);
    
    default Checkout findByUserEmailAndProductIdWithLogging(String userEmail, Long productId) {
    	logger.info("Searching for CheckOut by userEmail: {} and productId: {}",userEmail,productId);
    	return findByUserEmailAndProductId(userEmail, productId);
    }
    
    default List<Checkout> findProductsByUserEmailWithLogging(String userEmail){
        logger.info("Searching for Checkouts by userEmail: {}",userEmail);
        return findProductsByUserEmail(userEmail);
    }
    
    default void deleteAllByProductIdWithLogging(@Param("product_id") Long productId) {
    	logger.info("Deleting Checkout by productId: {}",productId);
    	deleteAllByProductId(productId);
    }
    
    
}
