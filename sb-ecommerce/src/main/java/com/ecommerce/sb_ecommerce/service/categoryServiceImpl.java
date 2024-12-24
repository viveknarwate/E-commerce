package com.ecommerce.sb_ecommerce.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.sb_ecommerce.exception.APIException;
import com.ecommerce.sb_ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.sb_ecommerce.model.*;
import com.ecommerce.sb_ecommerce.payload.CategoryDTO;
import com.ecommerce.sb_ecommerce.payload.CategoryResponse;
import com.ecommerce.sb_ecommerce.repository.CategoryRepository;

@Service
public class categoryServiceImpl implements categoryService {
    
	//List<Category>categories =new ArrayList<>();
	private Long nextId=1L;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy, String sortOrder) {
		//return categories;
		
		Sort sortByAndOrder =sortOrder.equalsIgnoreCase("asc")
				?Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		Pageable pageDetails = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
		Page<Category>categoryPage = categoryRepository.findAll(pageDetails);
		
		//List<Category>categories=categoryRepository.findAll();
		List<Category>categories=categoryPage.getContent();
		if(categories.isEmpty()) {
			throw new APIException("No category created till now");
		}
		
		List<CategoryDTO>categoryDTOS=categories.stream()
				.map(category -> modelMapper.map(category, CategoryDTO.class))
				.toList();

		CategoryResponse categoryResponse =new CategoryResponse();
		categoryResponse.setContent(categoryDTOS);
		categoryResponse.setPageNumber(categoryPage.getNumber());
		categoryResponse.setPageSize(categoryPage.getSize());
		categoryResponse.setTotalElements(categoryPage.getTotalElements());
		categoryResponse.setTotalPAges(categoryPage.getTotalPages());
		categoryResponse.setLastPage(categoryPage.isLast());
		return categoryResponse;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		//category.setCategoryId(nextId++);
		//categories.add(category);
		Category category=modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
		if(savedCategory != null) {
			throw new APIException("Category with Category Name: "+ categoryDTO.getCategoryName()+" already esist");
		}
		Category savedCategories=categoryRepository.save(category);
		CategoryDTO savedCategoryDTO =modelMapper.map(savedCategories, CategoryDTO.class);
		return savedCategoryDTO;
		}

	@Override
	public CategoryDTO deleteCategory(Long categoryId) {
		List<Category> categories = categoryRepository.findAll();
		
		Category category=categories .stream()
				          .filter(c -> c.getCategoryId().equals(categoryId))
				          .findFirst().orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
		
		//categories.remove(category);
		categoryRepository.delete(category);
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
		
//		List<Category> categories =categoryRepository.findAll();
//		
//		Category existingCategory=categories.stream()
//		          .filter(c -> c.getCategoryId().equals(categoryId))
//		          .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with:"+
//		            categoryId  +" Not Found"));
//		if(category.getCategoryName().isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nessary Details are not present");
//		}
//        if(existingCategory != null) {
//        	existingCategory.setCategoryName(category.getCategoryName());
//        }
		
		Optional<Category> savedCategoryOptional =categoryRepository.findById(categoryId);
		Category savedCategory = savedCategoryOptional
				                 .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
		Category category=modelMapper.map(categoryDTO, Category.class);
		category.setCategoryId(categoryId);
		savedCategory= categoryRepository.save(category);
        
		return modelMapper.map(savedCategory,CategoryDTO.class);
	}

}
