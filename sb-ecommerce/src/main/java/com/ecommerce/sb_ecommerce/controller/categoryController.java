package com.ecommerce.sb_ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.sb_ecommerce.configuration.AppConstants;
import com.ecommerce.sb_ecommerce.model.Category;
import com.ecommerce.sb_ecommerce.payload.CategoryDTO;
import com.ecommerce.sb_ecommerce.payload.CategoryResponse;
import com.ecommerce.sb_ecommerce.service.categoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class categoryController {
	
	@Autowired
	private categoryService categoryService;
	
	@GetMapping("/public/categories")
	public ResponseEntity<CategoryResponse> getAllCategories(
			@RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(name="sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
			@RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_DIR,required = false)String sortOrder) {
		CategoryResponse categoryResponse =categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
	}
	
	@PostMapping("/admin/categories")
	public  ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO savedCategoryDTO=categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(savedCategoryDTO ,HttpStatus.CREATED);
	}
	 
	@DeleteMapping("/admin/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@Valid @PathVariable Long categoryId) {
//		try {
//		     String status=categoryService.deleteCategory(categoryId);
//		     return new ResponseEntity<>(status,HttpStatus.OK);
//		}catch(ResponseStatusException e) {
//			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//		}
		
		CategoryDTO deleted=categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(deleted,HttpStatus.OK);
	}
	
	
	//@RequestMapping(value="/admin/categories/{categoryId}",method=RequestMethod.POST)
	@PutMapping("/admin/categories/{categoryId}")
	public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO ,@PathVariable Long categoryId){
//		try {
//		     Category savedCategory=categoryService.updateCategory(category,categoryId);
//		     return new ResponseEntity<>("Category with categoryId:"+categoryId+" updated successfully",HttpStatus.OK);
//		}catch(ResponseStatusException e) {
//			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//		}
		
		 CategoryDTO savedCategory=categoryService.updateCategory(categoryDTO,categoryId);
	     return new ResponseEntity<>(savedCategory,HttpStatus.OK);
	}

}
