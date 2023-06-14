package telran.java47.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.accounting.dao.UserRepository;
import telran.java47.accounting.dto.RolesDto;
import telran.java47.accounting.dto.UserDto;
import telran.java47.accounting.dto.UserRegisterDto;
import telran.java47.accounting.dto.UserUpdateDto;
import telran.java47.accounting.exception.UserNotFoundException;
import telran.java47.accounting.model.Roles;
import telran.java47.accounting.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	final UserRepository userRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto userRegister(UserRegisterDto userRegisterDto) {
		if (userRegisterDto.getLogin() == null || userRegisterDto.getPassword() == null
				|| userRegisterDto.getFirstName() == null || userRegisterDto.getLastName() == null)
			throw new RuntimeException(
					"The fields \"login\", \"password\", \"First name\" and \"Last name\" must be filled");
		if (userRepository.existsByLogin(userRegisterDto.getLogin())) {
			throw new RuntimeException("User with this login already exists");
		}
		User user = modelMapper.map(userRegisterDto, User.class);
		userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto userDelete(String login) {
		User user = userRepository.findByLogin(login);
		userRepository.delete(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto userUpdate(String login, UserUpdateDto userUpdateDto) {
		User user = userRepository.findByLogin(login);
		userRepository.save(user.userUpdate(userUpdateDto.getFirstName(), userUpdateDto.getLastName()));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RolesDto changeRolesList(String login, Roles role, boolean isAddRole) {
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		boolean result = isAddRole ? user.addRole(role) : user.deleteRole(role);
		if (result)
			userRepository.save(user);
		return new RolesDto(login, user.getRoles());
	}

	@Override
	public void changePassword(String login, String newPassword) {
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		user.setPassword(newPassword);
		userRepository.save(user);
	}
}
