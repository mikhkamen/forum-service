package telran.java47.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.user.dao.UserRepository;
import telran.java47.user.dto.UserDto;
import telran.java47.user.dto.UserLoginDto;
import telran.java47.user.dto.UserRegisterDto;
import telran.java47.user.dto.UserUpdateDto;
import telran.java47.user.model.Roles;
import telran.java47.user.model.User;

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
	public UserDto userLogin(UserLoginDto userLoginDto) {
		User user = userRepository.findByLogin(userLoginDto.getLogin());
		if (user.getPassword().equals(userLoginDto.getPassword()))
			return modelMapper.map(user, UserDto.class);
		else
			return null;
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
	public UserDto addRole(String login, Roles role) {
		User user = userRepository.findByLogin(login);
		if (user.addRole(role)) {
			userRepository.save(user);
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteRole(String login, Roles role) {
		User user = userRepository.findByLogin(login);
		if (user.deleteRole(role))
			userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void changePass(UserLoginDto userLoginDto) {
		User user = userRepository.findByLogin(userLoginDto.getLogin());
		user.setPassword(userLoginDto.getPassword());
		userRepository.save(user);
	}

}
