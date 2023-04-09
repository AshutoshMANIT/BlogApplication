package com.app.blog.payloads;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {

	
	private Integer categoryId;

	@NotBlank
	@Size(min=4)
	private String categoryTitle;
	@NotBlank
	@Size(min=10)
	private String categorydescription;

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(Integer categoryId, String categoryTitle, String categorydescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categorydescription = categorydescription;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategorydescription() {
		return categorydescription;
	}

	public void setCategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}
	
	
}
