package com.ecommerce.sb_ecommerce.service;

import java.util.List;

import com.ecommerce.sb_ecommerce.model.Category;
import com.ecommerce.sb_ecommerce.payload.CategoryDTO;
import com.ecommerce.sb_ecommerce.payload.CategoryResponse;

public interface categoryService {

	CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize ,String sortBy, String sortOrder);
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	CategoryDTO deleteCategory(Long categoryId);
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
