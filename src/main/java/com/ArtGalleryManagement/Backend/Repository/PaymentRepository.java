package com.ArtGalleryManagement.Backend.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ArtGalleryManagement.Backend.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
	Logger logger =LoggerFactory.getLogger(PaymentRepository.class);
	Payment findByUserEmail(String userEmail);
	
	default Payment findByUserEmailWithLogging(String userEmail) {
		logger.info("Searching for Payment by userEmail: {}",userEmail);
		return findByUserEmail(userEmail);
	}
	
}
