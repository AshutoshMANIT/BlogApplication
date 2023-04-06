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

import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.UserDto;
import com.app.blog.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//post-create user------------------------------
//yaha User nahi UserDto use kara bcz ho sakta hai hame pasword nahi dena hai 
	//tho UserDto class se password hata denge tho User class intact rahegi 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
	UserDto createdUserDto=this.userService.createUser(userDto);			

	return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//put-update user
	@PutMapping("/{Id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("Id") Integer userId)
	{
		UserDto updatedUser=this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	//delete-delete user
	@DeleteMapping("/{Id}")
	public ResponseEntity<?> deleteUser(@PathVariable("Id") Integer usetId) {
		
		this.userService.deleteUser(usetId); 
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully",true),HttpStatus.OK);
	}
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
	List<UserDto> allUsers=this.userService.getAllUsers();	
		//return new ResponseEntity<>(allUsers,HttpStatus.OK);
		return ResponseEntity.ok(allUsers);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getAllUser(@PathVariable Integer userId){
		
		UserDto user=this.userService.getUserById(userId);
		
		return ResponseEntity.ok(user);
	
	}
	
}
