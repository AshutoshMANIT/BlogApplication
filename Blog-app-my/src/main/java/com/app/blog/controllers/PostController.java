package com.app.blog.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.config.AppConstants;
import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;
import com.app.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

		@Autowired
	private PostService postService;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
	PostDto createdpost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdpost, HttpStatus.CREATED);
	}
	
//get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
	List<PostDto> postByUser= this.postService.getPostsByUser(userId);
		
	return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
		
	
	}
	
//get by categroy

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
	List<PostDto> postByCategory= this.postService.getPostsByCategory(categoryId);
		
	return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);
		
	
	}


//get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=/* post id se default sort hoga*//*"postId"*/AppConstants.SORT_BY,required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=/*"asc"*/AppConstants.SORT_DIR,required=false)String sortDir
			
			){
		
	PostResponse allPost=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	
	return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}


//get post details by id
	
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getAllPost(@PathVariable Integer id){
		
		PostDto allPost=this.postService.getPostById(id);
	
	return new ResponseEntity<PostDto>(allPost,HttpStatus.OK);
	}

//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("post is deleted",true);
	}

//update post
	@PutMapping("/posts/{postId}")
   public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
		   @PathVariable Integer postId) {
	   PostDto postDtoo=this.postService.updatePost(postDto, postId);
	   
	   return new ResponseEntity<PostDto>(postDtoo,HttpStatus.OK);
   }

//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable String keywords)
	{
		
		List<PostDto> postdto=  this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(postdto,HttpStatus.OK);
	}




}
