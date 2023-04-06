package com.app.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.UserDto;
import com.app.blog.repositories.UserRepo;
import com.app.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserRepo userRepo;
	@Autowired
private ModelMapper modelMapper;

@Override
	public UserDto createUser(UserDto userdto) {
		User user=this.dtoToUser(userdto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("User","id",userId));
	   
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User updateduser=this.userRepo.save(user);
		
		
		return this.userToDto(updateduser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
//hame List chiyee userdto ki abb tho hum users ko convert karnge stram api ki help se		
List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());	
	return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		return user;
		
		
		/*old syntax without ModelMapper
		 User user=new User();
		  user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setId(userDto.getId());
		return user;*/
		
	}
private UserDto userToDto(User user) {
	UserDto userdto=this.modelMapper.map(user,UserDto.class);
	return userdto;
	/* old way without ModelMapper
	UserDto userdto=new UserDto();
	userdto.setAbout(user.getAbout());
	userdto.setEmail(user.getEmail());
	userdto.setId(user.getId());
	userdto.setName(user.getName());
	userdto.setPassword(user.getPassword());
	return userdto;*/
}


}
