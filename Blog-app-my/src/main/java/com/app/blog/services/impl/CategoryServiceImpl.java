package com.app.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Category;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.payloads.UserDto;
import com.app.blog.repositories.CategoryRepo;
import com.app.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=categoryDtoTocategory(categoryDto);
		Category catdto=this.categoryRepo.save(cat);
		
		return categoryTocategoryDto(cat);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer caregoryId) {
		
		Category oldCategory=this.categoryRepo.findById(caregoryId).orElseThrow(()->new ResourceNotFoundException("category","id",caregoryId));
		oldCategory.setCategorydescription(categoryDto.getCategorydescription());
		oldCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category saveCat=this.categoryRepo.save(oldCategory);
		return categoryTocategoryDto(saveCat) ;
	}

	@Override
	public void deleteCategory(Integer caregoryId) {
		Category cat=this.categoryRepo.findById(caregoryId).orElseThrow(()->new ResourceNotFoundException("category","id",caregoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer caregoryId) {
		Category cat=this.categoryRepo.findById(caregoryId).orElseThrow(()->new ResourceNotFoundException("category","id",caregoryId));
		return categoryTocategoryDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCat=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=allCat.stream().map(cat->this.categoryTocategoryDto(cat)).collect(Collectors.toList());
		return catDtos;
	} 



	public Category categoryDtoTocategory(CategoryDto categoryDto) {
		
		Category category=this.modelMapper.map(categoryDto,Category.class);
		
		return category;
	}
	

	public CategoryDto categoryTocategoryDto(Category category) {
		
		CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
		
		return categoryDto;
	}
	
}
