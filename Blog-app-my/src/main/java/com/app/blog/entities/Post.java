package com.app.blog.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title",length=100,nullable=false)
	private String title;
	
	@Column(length=10000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	private User user;
	
	
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;



	public Integer getPostId() {
		return postId;
	}



	public void setPostId(Integer postId) {
		this.postId = postId;
	}



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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Post(Integer postId, String title, String content, String imageName, Date addedDate, User user,
			Category category) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
	}



	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
