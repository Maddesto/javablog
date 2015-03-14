package maddesto.controller;

import javax.validation.Valid;

import maddesto.entity.User;
import maddesto.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User constructUser(){
		return new User();
	}
	
	@RequestMapping
	public String register(){
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(user);
		return "redirect:/registration.html?success=true";
	}
	
	/*
	 * @RequestParam takes param from url (...html?param=value&..)
	 * */
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String username){
		Boolean available = userService.find(username) == null;
		return available.toString();	
	}
	
}
