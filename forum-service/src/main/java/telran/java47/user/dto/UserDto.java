package telran.java47.user.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java47.user.model.Roles;

@Getter
@AllArgsConstructor

public class UserDto {
	String login;
    String firstName;
    String lastName;
    Set<Roles> roles;
	
    public UserDto() {
		this.roles = new HashSet<>();
		roles.add(Roles.USER);
	}

	public UserDto(String login, String firstName, String lastName) {
		this();
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
	}
    
    
}
