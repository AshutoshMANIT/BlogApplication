package com.app.blog.services;

import java.util.List;

import com.app.blog.entities.Post;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;

public interface PostService {

	//create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	//delete
	
	void deletePost(Integer postId);
	//get all post
	
//before pagination-->	List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get Single post
	PostDto getPostById(Integer postId);

//get  all post by category
	
	List<PostDto> getPostsByCategory(Integer id);
	
	//get all post by user
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//search post by keyword
	List<PostDto> searchPosts(String keyword);
	
	
	
}
