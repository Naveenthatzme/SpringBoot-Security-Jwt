package com.app.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.IService.IUserService;
import com.app.Repository.UserRepository;
import com.app.model.User;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	/*
	 * this is for  Registration process method
	 * 
	 */
	@Override
	public Integer save(User user) {
			//reading pwd
			String pwd=user.getUserPwd();
		
			//convert into unreadable format
			pwd=encoder.encode(pwd);
			
			//set back to model class object
				user.setUserPwd(pwd);
			//save into DB
				return repo.save(user).getId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		//load model class user
		com.app.model.User user=repo.findByUserMail(username);
		
		//read roles from model class
		List<String> roles=user.getRoles();
		
		//create set for grantedAuthorities
		Set<GrantedAuthority> authorities= new HashSet<>();
		
		//convert string role into GrantedAuthority object
		for(String role:roles) {
			
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		//create spring security user class object
		org.springframework.security.core.userdetails.User uob=new org.springframework.security.core.userdetails.User
				
				(username, user.getUserPwd(), authorities);
		
				
				return null;
	}
}
