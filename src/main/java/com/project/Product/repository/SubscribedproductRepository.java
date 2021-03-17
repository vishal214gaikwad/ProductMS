package com.project.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Product.entity.Subscribedproduct;

public interface SubscribedproductRepository extends JpaRepository<Subscribedproduct, Integer>{
	public List<Subscribedproduct> findByBuyerid(int buyerId);
}
