package com.ecommerce.sb_ecommerce.payload;

public class CategoryDTO {
      private Long categoryID;
      private String categoryName;
	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public CategoryDTO(Long categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
      
    public CategoryDTO() {
    	
    }
}
