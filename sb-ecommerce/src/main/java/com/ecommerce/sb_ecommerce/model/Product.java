package com.ecommerce.sb_ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productId;
	@NotBlank
	@Size(min=3,message="Product name contain atleast 3 characters")
	private String productName;
	@NotBlank
	@Size(min=6,message="Description contain atleast 6 characters")
	private String description;
	private Integer quantity;
	private double price;
	private double specialPrice;
	private double discount;
	private String image;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
    
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long productId, String productName, String description, Integer quantity, double price,
			double specialPrice, Category category,double discount,String image) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.specialPrice = specialPrice;
		this.category = category;
		this.discount = discount;
		this.image = image;
	}
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private Users user;
}
