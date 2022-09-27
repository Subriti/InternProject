package com.example.project.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.User;
import com.example.project.Service.UserService;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/showUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	// Single item
	@GetMapping(path= "/findUser/{userId}")
	public User findUser(@PathVariable int userId) {
		return userService.findUser(userId);
	}
	
	@PostMapping(path= "/loginUser")
	public String loginUser(@RequestBody Map<String, Object> body) {
		return userService.Login(body.get("user_name").toString(),body.get("password").toString());
	}
	
	@PostMapping(path= "/loginHashedUser")
	public String loginHashedUser(@RequestBody Map<String, Object> body) {
		return userService.LoginHashedPassword(body.get("user_name").toString(),body.get("password").toString());
	}

	@PostMapping("/addUsers")
	public String addNewUser(@RequestBody User user) {
		return userService.addNewUser(user);
	}

	@DeleteMapping(path = "/deleteUsers/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}

	@PutMapping(path = "/updateUsers/{userId}")
	public String updateUser(@PathVariable int userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}
	
	@PutMapping(path = "/resetPassword/{userId}")
	public String resetPassword(@PathVariable int userId, @RequestParam(required = false) String user_name, @RequestParam(required = true) String old_password, @RequestParam(required = true) String new_password) {
		return userService.resetPassword(userId, user_name, old_password, new_password);
	}
	
	@PutMapping(path = "/resetPasswordChange/{userId}")
	public String requestPasswordChange(@PathVariable int userId, @RequestBody Map<String,Object> body) {
        return userService.resetPassword(userId, body.get("user_name").toString(), body.get("old_password").toString(), body.get("new_password").toString());
	}
	
	@PutMapping(path = "/forgotPassword/{userId}")
	public String forgotPassword(@PathVariable int userId) {
        return userService.forgotPassword(userId);
	}
}
