package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer caregoryId);
	//delete
	
	public void deleteCategory(Integer caregoryId);
	//get
	public CategoryDto getCategory(Integer caregoryId);
	//get all
	public List<CategoryDto> getAllCategory();
	
}
