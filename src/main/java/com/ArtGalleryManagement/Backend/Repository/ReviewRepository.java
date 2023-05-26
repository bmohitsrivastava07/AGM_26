package com.ArtGalleryManagement.Backend.Repository;

import com.ArtGalleryManagement.Backend.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public interface ReviewRepository extends JpaRepository<Review, Long> {

	Logger logger=LoggerFactory.getLogger(ReviewRepository.class);
    Page<Review> findByProductId(@RequestParam("product_id") Long productId,
                              Pageable pageable);

    Review findByUserEmailAndProductId(String userEmail, Long productId);

    @Modifying
    @Query("delete from Review where product_id in :product_id")
    void deleteAllByProductId(@Param("product_id") Long productId);
    
    default Page<Review> findByProductIdWithLogging(@RequestParam("product_id") Long productId,
            Pageable pageable){
    	logger.info("Searching for reviews by product id: {}",productId);
    	return findByProductId(productId,pageable);
    }
    
    default Review findByUserEmailAndProductIdWithLogging(String userEmail, Long productId) {
    	logger.info("Searching for review by userEmail: {} and productId: {}",userEmail,productId);
    	return findByUserEmailAndProductId(userEmail,productId);
    }
    
    default void deleteAllByProductIdWithLogging(@Param("product_id") Long productId) {
    	logger.info("Deleting review by productId: {}",productId);
    	deleteAllByProductId(productId);
    }
    
}
