package telran.java47.user.model;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java47.post.model.Post;

@Data
@Document(collection = "users")
@EqualsAndHashCode(of = "login")
public class User {
	String id;
	String login;
	String password;
	@Setter
	String firstName;
	@Setter
	String lastName;
	Set<Roles> roles;

	ModelMapper modelMapper;
	
	public User() {
		this.roles = new HashSet<>();
	}

	public User(String login, String password, String firstName, String lastName) {
		this();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		roles.add(Roles.USER); 
	}
	
	public boolean addRole(Roles role) {
		return roles.add(role);
	}
	 public boolean deleteRole(Roles role) {		 
		return roles.remove(role);		
	}

}
