package com.app.blog.payloads;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.blog.entities.Category;
import com.app.blog.entities.User;

public class PostDto {

	
	private Integer postId;
	public PostDto(Integer postId, String title, String content, String imageName, Date addedDate, Category category,
			User user) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	private String title;
	
	
	private String content;
	
	
	//----------
private String imageName;

private Date addedDate;

private Category category;

private User user;
	

	public String getImageName() {
	return imageName;
}


public void setImageName(String imageName) {
	this.imageName = imageName;
}


public Date getAddedDate() {
	return addedDate;
}


public void setAddedDate(Date addedDate) {
	this.addedDate = addedDate;
}


public Category getCategory() {
	return category;
}


public void setCategory(Category category) {
	this.category = category;
}


public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


	//---------------
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public PostDto(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}


	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//no need->private String imageName;
	
	//automatic aygi->private Date addedDate;
	
	
//alag se lenge inko url mai->	private User user&private Category category;

	
	
	
}
