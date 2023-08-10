package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.crud.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}


