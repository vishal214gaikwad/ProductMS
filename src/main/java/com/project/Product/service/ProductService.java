package com.project.Product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Product.Validator.Validator;
import com.project.Product.dto.ProductDTO;
import com.project.Product.entity.Product;
import com.project.Product.repository.ProductRepository;
import com.project.Product.repository.SubscribedproductRepository;

@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SubscribedproductRepository subscribedproductRepository;
	
	@Autowired
	Validator validator;
	
	@Autowired
	Environment environment;
	
	
	//Fetch all products list
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		System.out.println("In service");
		List<Product>products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product product:products) {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		}
		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	//fetch product list by category
	public List<ProductDTO> getProductByCategory(@PathVariable String category) throws ProductMSException {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product product:products) {
			productDTOs.add(ProductDTO.valueOf(product));
		}
		logger.info("Product details according to category : {}", productDTOs);
		
		return productDTOs;
	}

	//fetch product list by name
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException {
		// TODO Auto-generated method stub
		
		List<Product> products = productRepository.findByProductname(productname);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product product:products) {
			productDTOs.add(ProductDTO.valueOf(product));
		}
		logger.info("Product details according to product name : {}", productDTOs);
		return productDTOs;
	}

	//fetch product list by productid
	public ProductDTO getProdByProdId(Integer prodid) {
		Product product = productRepository.findByProdid(prodid);
		ProductDTO productDTO=null;
		if(product!=null) {
			productDTO = ProductDTO.valueOf(product);
		}
		return productDTO;
	}

	//add a product
	public void addProduct(ProductDTO productDTO) throws Exception {
		validator.validateProduct(productDTO);
		List<Product> product = productRepository.findByProductname(productDTO.getProductname());
		if(product.isEmpty()) {
			Product products = productDTO.createEntity();
			productRepository.save(products);
		}
		else {
			throw new Exception(environment.getProperty("PRODUCT_ALREADY_EXISTS"));
		}
	}
	
	//remove a product
	public boolean removeProduct(Integer productid)
	{
		Optional<Product> product = productRepository.findById(productid);
		if(product.isPresent()) {
			Product product1 = product.get();
			productRepository.delete(product1);
			return true;
		}else {
			return false;
		}
	}

	//update stock
	public void updateStock(ProductDTO productDTO) throws Exception {
		validator.validateProduct(productDTO);
		Product product = productRepository.findByProdid(productDTO.getProdid());
		if(product!=null)
		{
			//Product product1 = productDTO.createEntity();
			product.setStock(productDTO.getStock());
			productRepository.save(product);
		}
	}



}
