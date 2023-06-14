package telran.java47.accounting.controller;

import java.security.Principal;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.accounting.dto.RolesDto;
import telran.java47.accounting.dto.UserDto;
import telran.java47.accounting.dto.UserRegisterDto;
import telran.java47.accounting.dto.UserUpdateDto;
import telran.java47.accounting.model.Roles;
import telran.java47.accounting.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
	
	final UserService userService;

	@PostMapping("/register")
	public UserDto userRegister(@RequestBody UserRegisterDto userRegisterDto) {
		return userService.userRegister(userRegisterDto);
	}
	
	@PostMapping("/login")
	public UserDto login(Principal principal) {
		return userService.getUser(principal.getName());
	}
	
	@GetMapping("/user/{login}")
	public UserDto getUser(@PathVariable String login) {
		return userService.getUser(login);
	}

	@DeleteMapping("/user/{user}")
	public UserDto userDelete(@PathVariable String user) {
		return userService.userDelete(user);
	}

	@PutMapping("/user/{user}")
	public UserDto userUpdate(@PathVariable String user, @RequestBody UserUpdateDto userUpdateDto) {
		return userService.userUpdate(user, userUpdateDto);
	}

	@PutMapping("/user/{user}/role/{role}")
	public RolesDto addRole(@PathVariable String user, @PathVariable String role) {
		Roles userRole = Roles.valueOf(role.toUpperCase());
		return userService.changeRolesList(user, userRole, true);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public RolesDto deleteRole(@PathVariable String user, @PathVariable String role) {
		Roles userRole = Roles.valueOf(role.toUpperCase());
		return userService.changeRolesList(user, userRole, false);
	}

	@PutMapping("/user/password")
	public void changePass(Principal principal, @RequestHeader("X-Password") String newPassword) {
		userService.changePassword(principal.getName(), newPassword);
	}
}
