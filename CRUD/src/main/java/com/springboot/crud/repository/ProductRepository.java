package com.springboot.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.crud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(Long categoryId);
	
}
