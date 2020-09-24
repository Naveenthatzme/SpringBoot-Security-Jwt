package com.app.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.IUserService;
import com.app.jwtUtil.JwtUtil;
import com.app.model.User;
import com.app.model.UserRequest;
import com.app.model.UserResponse;

@RestController
@RequestMapping("user")
public class UserRestController {

	@Autowired
	private IUserService service;

	// it will verify username password
	@Autowired
	private AuthenticationManager manger;
	
	@Autowired
	private JwtUtil util;
	//1.save 
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody User user){
		
		Integer id=service.save(user);
		 return ResponseEntity.ok("User saved with id"+id);
	}
	
	
	//2. validate login user
	@PostMapping("/login")
	public ResponseEntity<UserResponse> logincheck(@RequestBody UserRequest request){
		//it will cross check with database using userdetailsservice(I)
			manger.authenticate(new UsernamePasswordAuthenticationToken
							(request.getUsername(), request.getPassword()));
			
			String token =util.generatetoken(request.getUsername());
		return ResponseEntity.ok(new UserResponse("SUCCESS",token));
	}

}
