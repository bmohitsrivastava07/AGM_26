package com.ArtGalleryManagement.Backend.Controller;

import com.ArtGalleryManagement.Backend.Entity.*;
import com.ArtGalleryManagement.Backend.ResponseModels.*;
import com.ArtGalleryManagement.Backend.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	  private static final Logger
	  logger=LoggerFactory.getLogger(ProductController.class);
	 	private ProductServiceImpl productService;

	@Autowired
	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}

	@GetMapping("/secure/currentloans")
	public List<ShelfCurrentLoansResponse> currentLoans() throws Exception {
		String userEmail = "mohit@gmail.com";
	
	 logger.info("Fetching current loans for user: {}",userEmail);
		 		return productService.currentLoans(userEmail);
	}

	@GetMapping("/secure/currentloans/count")
	public int currentLoansCount() {
		String userEmail = "mohit@gmail.com";
		
		 logger.info("Fetching current loans count for user: {}",userEmail);
				return productService.currentLoansCount(userEmail);
	}

	@GetMapping("/secure/ischeckedout/byuser")
	public Boolean checkoutProductByUser(@RequestParam Long productId) {
		String userEmail = "mohit@gmail.com";
		
		 logger.info("Checking if product with id {} is checked out by user: {}"
		 ,productId, userEmail);
				return productService.checkoutProductByUser(userEmail, productId);
	}

	@PutMapping("/secure/checkout")
	public Product checkoutProduct(@RequestParam Long productId) throws Exception {
		String userEmail = "mohit@gmail.com";
		
		 logger.info("Checking out product with id: {} for user: {}",productId,
		 userEmail);
				return productService.checkoutProduct(userEmail, productId);
	}

	@PutMapping("/secure/return")
	public void returnProduct(@RequestParam Long productId) throws Exception {
		String userEmail = "mohit@gmail.com";
		
		 logger.info("Returning product with id: {} for user: {}",productId,userEmail)
		 ;
				productService.returnProduct(userEmail, productId);
	}

	@PutMapping("/secure/renew/loan")
	public void renewLoan(@RequestParam Long productId) throws Exception {
		String userEmail = "mohit@gmail.com";
		
		 logger.info("Renewing loan for product with id: {} for user: {}",productId,
		 userEmail);
				productService.renewLoan(userEmail, productId);
	}

}
