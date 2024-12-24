package com.ecommerce.sb_ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecommerce.configuration.AppConstants;
import com.ecommerce.sb_ecommerce.payload.ProductDTO;
import com.ecommerce.sb_ecommerce.payload.ProductResponse;
import com.ecommerce.sb_ecommerce.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/admin/categories/{categoryId}/product")
	public ResponseEntity<ProductDTO>addProduct(@Valid @RequestBody ProductDTO productDTO,
			                                    @PathVariable Long categoryId){
		ProductDTO savedProductDTO=productService.addProduct(productDTO,categoryId);
		return new ResponseEntity<>(savedProductDTO,HttpStatus.CREATED) ;
	}
	
	@GetMapping("public/products")
	public ResponseEntity<ProductResponse> getAllProducts(@RequestParam (name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required=false)Integer pageNumber, 
			                                              @RequestParam (name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required=false)Integer pageSize,
			                                              @RequestParam (name="sortBy",defaultValue = AppConstants.SORT_PRODUCTS_BY,required=false)String sortBy,
			                                              @RequestParam (name="sortOrder",defaultValue = AppConstants.SORT_DIR,required=false)String sortOrder){
		ProductResponse productResponse= productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@GetMapping("/public/categories/{categoryId}/products")
	public ResponseEntity<ProductResponse>getProductsByCategory(
			@RequestParam (name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required=false)Integer pageNumber, 
            @RequestParam (name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required=false)Integer pageSize,
            @RequestParam (name="sortBy",defaultValue = AppConstants.SORT_PRODUCTS_BY,required=false)String sortBy,
            @RequestParam (name="sortOrder",defaultValue = AppConstants.SORT_DIR,required=false)String sortOrder,@PathVariable Long categoryId){
		ProductResponse productResponse = productService.searchByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(productResponse ,HttpStatus.OK);
	}

	@GetMapping("/public/products/keyword/{Keyword}")
	public ResponseEntity<ProductResponse>getProductByKeyword(@RequestParam (name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required=false)Integer pageNumber, 
            @RequestParam (name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required=false)Integer pageSize,
            @RequestParam (name="sortBy",defaultValue = AppConstants.SORT_PRODUCTS_BY,required=false)String sortBy,
            @RequestParam (name="sortOrder",defaultValue = AppConstants.SORT_DIR,required=false)String sortOrder,@PathVariable String Keyword){
		ProductResponse productResponse = productService.searchProductByKeyword(Keyword,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(productResponse ,HttpStatus.FOUND);
	}
	
	@PutMapping("/admin/products/{ProductId}")
	public ResponseEntity<ProductDTO>updateProductByProductId(@Valid @RequestBody ProductDTO productDTO,
			@PathVariable Long ProductId){
		ProductDTO updateproductDTO = productService.updateProductByProductId(ProductId,productDTO);
		return new ResponseEntity<>(updateproductDTO,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/products/{productId}")
	public ResponseEntity<ProductDTO>deleteProduct(@PathVariable Long productId){
		ProductDTO deletedProduct = productService.deleteProduct(productId);
		return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
	}
	
	@PutMapping("/products/{productId}/image")
	public ResponseEntity<ProductDTO>updateProductImage(@PathVariable Long productId ,
			@RequestParam("Image")MultipartFile image) throws Exception{
		ProductDTO updateProductImage = productService.updateProductImage(productId,image);
		return new ResponseEntity<>(updateProductImage,HttpStatus.OK);
	}
}
