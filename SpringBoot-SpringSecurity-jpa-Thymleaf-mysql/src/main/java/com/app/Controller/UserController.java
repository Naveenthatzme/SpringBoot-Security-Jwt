package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.IService.IUserService;
import com.app.model.User;

@Controller
public class UserController {

	@Autowired
	private IUserService service;
	
	@GetMapping("/register")
	public String showreg() {
		
		return "UserRegister";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user,Model model) {
		
		Integer id=service.save(user);
		model.addAttribute("message", "SUCCESS FULLY SAVED WITH"+id);
		return "UserRegister";
	}
	
	@GetMapping("/home")
	public String showLogin() {
		
		return "HomePage";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		
		return "AdminPage";
	}
	
	@GetMapping("emp")
	public String showEmp() {
		
		return "EmpPage";
	}
	
	@GetMapping("/common")
	public String showcommon() {
		
		return "CommonPage";
	}
	
	@GetMapping("/mgr")
	public String manger() {
		
		return "ManagerPage";
	}
	
	@GetMapping("/denied")
	public String denied() {
		
		return "DeniedPage";
	}
}
