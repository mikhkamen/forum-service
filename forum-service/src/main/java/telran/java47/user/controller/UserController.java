package telran.java47.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.user.dto.UserDto;
import telran.java47.user.dto.UserLoginDto;
import telran.java47.user.dto.UserRegisterDto;
import telran.java47.user.dto.UserUpdateDto;
import telran.java47.user.model.Roles;
import telran.java47.user.service.UserService;

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
	public UserDto userLogin(@RequestBody UserLoginDto userLoginDto) {
		return userService.userLogin(userLoginDto);
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
	public UserDto addRole(@PathVariable String user, @PathVariable String role) {
		Roles userRole = Roles.valueOf(role.toUpperCase());
		return userService.addRole(user, userRole);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public UserDto deleteRole(@PathVariable String user, @PathVariable String role) {
		Roles userRole = Roles.valueOf(role.toUpperCase());
		return userService.deleteRole(user, userRole);
	}

	@PutMapping("/user/password")
	@ResponseStatus(HttpStatus.OK)
	public void changePass(@RequestBody UserLoginDto userLoginDto) {
		userService.changePass(userLoginDto);
	}
}
