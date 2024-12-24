package com.ecommerce.sb_ecommerce.payload;

import java.util.List;

public class CategoryResponse {
     private List<CategoryDTO>content;
     private Integer pageNumber;
     private Integer pageSize;
     private Long totalElements;
     private Integer totalPAges;
     private boolean lastPage;

     
     
    public List<CategoryDTO> getContent() {
		return content;
	}



	public void setContent(List<CategoryDTO> content) {
		this.content = content;
	}



	public Integer getPageNumber() {
		return pageNumber;
	}



	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}



	public Integer getPageSize() {
		return pageSize;
	}



	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}



	public Long getTotalElements() {
		return totalElements;
	}



	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}



	public Integer getTotalPAges() {
		return totalPAges;
	}



	public void setTotalPAges(Integer totalPAges) {
		this.totalPAges = totalPAges;
	}



	public boolean isLastPage() {
		return lastPage;
	}



	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

   

	public CategoryResponse(List<CategoryDTO> content, Integer pageNumber, Integer pageSize, Long totalElements,
			Integer totalPAges, boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPAges = totalPAges;
		this.lastPage = lastPage;
	}



	public CategoryResponse() {
    	
    }
}
