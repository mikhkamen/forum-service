package telran.java47.user.model;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Document(collection = "users")
@EqualsAndHashCode(of = {"login", "password"})
public class User {
	String id;
	String login;
	String password;
	@Setter
	String firstName;
	@Setter
	String lastName;
	Set<Roles> roles = new HashSet<>();

	ModelMapper modelMapper;

	public User(String login, String password, String firstName, String lastName) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		roles.add(Roles.USER); 
	}

}
