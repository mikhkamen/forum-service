package telran.java47.user.service;

import telran.java47.user.dto.UserDto;
import telran.java47.user.dto.UserLoginDto;
import telran.java47.user.dto.UserRegisterDto;
import telran.java47.user.dto.UserUpdateDto;
import telran.java47.user.model.Roles;

public interface UserService {

	UserDto userRegister(UserRegisterDto userRegisterDto);

	UserDto userLogin(UserLoginDto userLoginDto);

	UserDto userDelete(String user);

	UserDto userUpdate(String user, UserUpdateDto userUpdateDto);

	UserDto addRole(String user, Roles role);

	UserDto deleteRole(String user, Roles role);

	void changePass(UserLoginDto userLoginDto);
}
