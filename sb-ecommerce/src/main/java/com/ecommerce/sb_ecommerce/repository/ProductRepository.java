package com.ecommerce.sb_ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.sb_ecommerce.model.Category;
import com.ecommerce.sb_ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByCategoryOrderByPriceAsc(Category category, PageRequest pageDetails);

	Page<Product> findByProductNameLikeIgnoreCase(String keyword,PageRequest pageDetails);

	Product findByProductNameIgnoreCase(String productName);

}
