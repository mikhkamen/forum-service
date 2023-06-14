package telran.java47.accounting.service;

import telran.java47.accounting.dto.RolesDto;
import telran.java47.accounting.dto.UserDto;
import telran.java47.accounting.dto.UserRegisterDto;
import telran.java47.accounting.dto.UserUpdateDto;
import telran.java47.accounting.model.Roles;

public interface UserService {

	UserDto userRegister(UserRegisterDto userRegisterDto);
	
	UserDto getUser(String user);

	UserDto userDelete(String user);

	UserDto userUpdate(String user, UserUpdateDto userUpdateDto);

	RolesDto changeRolesList(String user, Roles role, boolean isAddRole);

	void changePassword(String login, String newPassword);
	
}
