package telran.java47.accounting.model;

import java.util.EnumSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import telran.java47.enums.Roles;

@Getter
@Document(collection = "users")
public class UserAccount {
	@Id
	String login;
	@Setter
	String password;
	@Setter 
	String firstName;
	@Setter
	String lastName;
	EnumSet<Roles> roles;
	
	public UserAccount() {
		this.roles = EnumSet.of(Roles.USER);
	}

	public UserAccount(String login, String password, String firstName, String lastName) {
		this();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean addRole(Roles role) {
		return roles.add(role);
	}

	public boolean removeRole(Roles role) {
		return roles.remove(role);
	}

}
