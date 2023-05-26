package com.ArtGalleryManagement.Backend.Repository;

import com.ArtGalleryManagement.Backend.Entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface HistoryRepository extends JpaRepository<History, Long> {
	Logger logger=LoggerFactory.getLogger(HistoryRepository.class);
    Page<History> findProductsByUserEmail(@RequestParam("email") String userEmail, Pageable pageable);
    
    default Page<History> findProductsByUserEmailWithLogging(@RequestParam("email") String userEmail, Pageable pageable){
    	logger.info("Searching for History by userEmail: {}",userEmail);
    	return findProductsByUserEmail(userEmail, pageable);
    }
}
