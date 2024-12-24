package com.ecommerce.sb_ecommerce.payload;


public class ProductDTO {
	
	private Long productId;
	private String productName;
	private String image;
	private Integer quantity;
	private double price;
	private  double discount;
	private double specialPrice;
	private String description;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductDTO(Long productId, String productName, String image, Integer quantity, double price, double discount,
			double specialPrice,String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.image = image;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.specialPrice = specialPrice;
		this.description=description;
	}
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
