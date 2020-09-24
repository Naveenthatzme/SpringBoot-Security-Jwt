package com.app.AppConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	
	@Autowired
	private UserDetailsService userdetailsservice;
	
	/*
	 * verify un pwd correct or not
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
			auth.userDetailsService(userdetailsservice).passwordEncoder(passwordencoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/register","/save","/home").permitAll()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/mgr").hasAuthority("MANAGER")
		.antMatchers("/common","/denied").authenticated()
		.anyRequest()
		.authenticated()
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/common",true)//successfull login go to common page
		
		.and()
		.logout()
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");
	}
}
