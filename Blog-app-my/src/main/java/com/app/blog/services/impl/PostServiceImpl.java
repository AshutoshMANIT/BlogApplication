package com.app.blog.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Category;
import com.app.blog.entities.Post;
import com.app.blog.entities.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;
import com.app.blog.repositories.CategoryRepo;
import com.app.blog.repositories.PostRepo;
import com.app.blog.repositories.UserRepo;
import com.app.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService  {

		@Autowired
	private PostRepo postRepo;
	
		@Autowired
		private ModelMapper modelMapper;
		@Autowired
		private CategoryRepo categoryRepo;
		@Autowired
		private UserRepo userRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryID",categoryId));
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		
		Post post=this.modelMapper.map(postDto,Post.class);
		//bcz postDto has only title and content tho hume baki cheez Post ki kudh set karni hogi yaha
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setUser(user);
		post.setCategory(category);
		
		this.postRepo.save(post);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		//post=this.modelMapper.map(postDto,Post.class);
		
		//post.setImageName("default.png");
		//post.setAddedDate(new Date());
	//	post.setUser(user);
		//post.setUser(user);
		//post.setCategory(category);
	Post updatedPost=	this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
/*This code was used when no PAGINATION was applied------------------------		
		public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
		List<Post> allPost=this.postRepo.findAll();
		
		List<PostDto> allPostDto=allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return allPostDto;
---------------------------------------------------------------------------*/	
//	Sort sort=null;
//		if(sortDir.equals("asc")) {
//	sort=Sort.by(sortBy).ascending();		
//	}
//	else {
//	sort=Sort.by(sortBy).descending();	
//	}
//	-----------yai oopar wali cheez ternary opearator through----S	
		Sort sort=sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
	
	Page<Post> pagePost=this.postRepo.findAll(p);
	List<Post> allPost=pagePost.getContent();
	List<PostDto> allPostDto=allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	
	PostResponse postResponse=new PostResponse();
	postResponse.setContent(allPostDto);
	postResponse.setPageNumber(pagePost.getNumber());
	postResponse.setPageSize(pagePost.getSize());
	postResponse.setTotalElements(pagePost.getTotalElements());
	postResponse.setTotalPages(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
	
	return postResponse;
	
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer id) {
		
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("cateogory","cat_id",id) );
				
		List<Post> posts=this.postRepo.findByCategory(category);
//	My method------------
//		List<PostDto> postDtos=new ArrayList<PostDto>();
//		for (Post i : posts) {
//			PostDto psotdto=this.modelMapper.map(i,PostDto.class);	 
//            postDtos.add(psotdto);
//            
//        }
//---------------------------
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());	
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub

		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user_id",userId));
		
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostDto> postsDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postsDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		//List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post,PostDto)).collect(Collectors.toList());
		List<PostDto> postsDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
		this.postRepo.delete(post);
		
	}

}
