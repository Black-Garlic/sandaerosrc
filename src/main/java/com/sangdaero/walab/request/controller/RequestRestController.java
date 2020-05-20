package com.sangdaero.walab.request.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sangdaero.walab.activity.dto.AppRequest;
import com.sangdaero.walab.request.service.RequestService;
import com.sangdaero.walab.user.application.dto.UserDto;
import com.sangdaero.walab.user.application.service.UserService;

@RestController
@RequestMapping("/requestdata")
public class RequestRestController {

	private RequestService mRequestService;
	private UserService mUserService;
	
	public RequestRestController(RequestService requestService, UserService userService) {
		mRequestService = requestService;
		mUserService = userService;
	}
	
	@PostMapping("/setStatus")
	public Byte setStatus(@RequestParam("id") Long id, @RequestParam("status") Byte status) {
		mRequestService.setStatus(id, status);
		
		return status;
	}
	
	@PostMapping("/register")
	public String register(@RequestBody AppRequest registerForm) {
		UserDto userDto = mUserService.createUser(registerForm.getEmail(), registerForm.getName());
		mRequestService.createRequest(registerForm.getId(), null, userDto);
		return "success";
	}

	@PostMapping("/newRegister")
	public String newRegister(@RequestBody AppRequest newRegisterForm) {
		UserDto userDto = mUserService.createUser(newRegisterForm.getEmail(), newRegisterForm.getName());
		mRequestService.createRequest(null, newRegisterForm.getId(), userDto);
		return "success";
	}
	
}
