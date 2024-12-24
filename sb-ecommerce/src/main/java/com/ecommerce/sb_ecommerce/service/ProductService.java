package com.ecommerce.sb_ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecommerce.model.Product;
import com.ecommerce.sb_ecommerce.payload.ProductDTO;
import com.ecommerce.sb_ecommerce.payload.ProductResponse;

public interface ProductService {

	ProductDTO addProduct(ProductDTO product, Long categoryId);

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductDTO updateProductByProductId(Long productId,ProductDTO product);

	ProductDTO deleteProduct(Long productId);

	ProductDTO updateProductImage(Long productId, MultipartFile image) throws Exception;

}
