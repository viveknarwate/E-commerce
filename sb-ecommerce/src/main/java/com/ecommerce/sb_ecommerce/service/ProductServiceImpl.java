package com.ecommerce.sb_ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecommerce.exception.APIException;
import com.ecommerce.sb_ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.sb_ecommerce.model.Category;
import com.ecommerce.sb_ecommerce.model.Product;
import com.ecommerce.sb_ecommerce.payload.ProductDTO;
import com.ecommerce.sb_ecommerce.payload.ProductResponse;
import com.ecommerce.sb_ecommerce.repository.CategoryRepository;
import com.ecommerce.sb_ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@Override
	public ProductDTO addProduct(ProductDTO productDTO, Long categoryId) {
		Product productDB = productRepository.findByProductNameIgnoreCase(productDTO.getProductName());
		if (productDB != null) {
			throw new APIException("Product with Product Name: " + productDTO.getProductName() + " already exist");
		}
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		Product product = modelMapper.map(productDTO, Product.class);
		product.setImage("default.png");
		product.setCategory(category);
		double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
		product.setSpecialPrice(specialPrice);
		Product savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductDTO.class);

	}

	@Override
	public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sortByAnOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		PageRequest pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnOrder);
		Page<Product> pageProducts = productRepository.findAll(pageDetails);

		// List<Product> products = productRepository.findAll();
		List<Product> products = pageProducts.getContent();
		List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
		if (products.isEmpty()) {
			throw new APIException("NO Products exist");
		}
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDTOS);
		productResponse.setPageNumber(pageProducts.getNumber());
		productResponse.setPageSize(pageProducts.getSize());
		productResponse.setTotalElements(pageProducts.getTotalElements());
		productResponse.setTotalPAges(pageProducts.getTotalPages());
		productResponse.setLastPage(pageProducts.isLast());
		return productResponse;
	}

	@Override
	public ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		Sort sortByAnOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		PageRequest pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnOrder);
		Page<Product> pageProducts = productRepository.findByCategoryOrderByPriceAsc(category,pageDetails);
		List<Product> products = pageProducts.getContent();
	    //List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
		List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDTOS);  
		productResponse.setPageNumber(pageProducts.getNumber());
		productResponse.setPageSize(pageProducts.getSize());
		productResponse.setTotalElements(pageProducts.getTotalElements());
		productResponse.setTotalPAges(pageProducts.getTotalPages());
		productResponse.setLastPage(pageProducts.isLast());
		return productResponse;
	}
     
	@Override
	public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort sortByAnOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		PageRequest pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnOrder);
		Page<Product> pageProducts = productRepository.findByProductNameLikeIgnoreCase("%" + keyword + "%",pageDetails);
		List<Product> products = pageProducts.getContent();
        //List<Product> products = productRepository.findByProductNameLikeIgnoreCase("%" + keyword + "%");
		
		List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
		if(products.isEmpty()){ 
			throw new APIException("Product not found with keyword: "+keyword);
		}
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDTOS);
		productResponse.setPageNumber(pageProducts.getNumber());
		productResponse.setPageSize(pageProducts.getSize());
		productResponse.setTotalElements(pageProducts.getTotalElements());
		productResponse.setTotalPAges(pageProducts.getTotalPages());
		productResponse.setLastPage(pageProducts.isLast());
		return productResponse;
	}

	@Override
	public ProductDTO updateProductByProductId(Long productId, ProductDTO productDTO) {
		Product productDB = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		Product product = modelMapper.map(productDTO, Product.class);
		productDB.setProductName(product.getProductName());
		productDB.setDescription(product.getDescription());
		productDB.setQuantity(product.getQuantity());
		productDB.setDiscount(product.getDiscount());
		productDB.setPrice(product.getPrice());
		productDB.setSpecialPrice(product.getSpecialPrice());

		Product savedProduct = productRepository.save(productDB);
		return modelMapper.map(savedProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO deleteProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

		productRepository.delete(product);
		return modelMapper.map(product, ProductDTO.class);
	}

	@Override
	public ProductDTO updateProductImage(Long productId, MultipartFile image) throws Exception {
		// get product from db
		Product productDB = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productID", productId));

		// upload image to server
		// Get the file name of upload image
		String fileName = fileService.uploadImage(path, image);
		// updating the new file name to the product
		productDB.setImage(fileName);
		Product updateProduct = productRepository.save(productDB);
		// return DTO after mapping
		return modelMapper.map(updateProduct, ProductDTO.class);
	}

}
