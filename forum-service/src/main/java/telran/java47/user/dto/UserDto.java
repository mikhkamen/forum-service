package telran.java47.user.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java47.user.model.Roles;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	String login;
    String firstName;
    String lastName;
    Set<Roles> roles;
}
