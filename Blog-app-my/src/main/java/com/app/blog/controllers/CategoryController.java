package com.app.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.entities.Category;
import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.services.CategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {

	@Autowired
private CategoryService catService;	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto)
	{
		
		CategoryDto catdto=this.catService.createCategory(dto);
		
		return new ResponseEntity<>(catdto,HttpStatus.OK);
		
		
	}

	//update
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catDto,@PathVariable("id") Integer catid){
		
		CategoryDto catdto=this.catService.updateCategory(catDto, catid);
		
		return  ResponseEntity.ok(catdto);
		
		
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
		this.catService.deleteCategory(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted succes category",true),HttpStatus.OK);
	}
	
	
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id){
		
		CategoryDto cat=this.catService.getCategory(id);
		
		return ResponseEntity.ok(cat);
		
	}
	
	
	//get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategory(){
		
		List<CategoryDto> lstCatDto=this.catService.getAllCategory();
		
		return ResponseEntity.ok(lstCatDto);
		
	}	






}
