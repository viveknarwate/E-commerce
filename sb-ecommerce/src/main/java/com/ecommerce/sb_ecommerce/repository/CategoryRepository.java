package com.ecommerce.sb_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.sb_ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByCategoryName(String categoryName);
}
