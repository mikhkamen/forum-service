package telran.java47.accounting.model;

import java.time.LocalDate;
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
	public LocalDate expirationDate;
	@Setter 
	String firstName;
	@Setter
	String lastName;
	EnumSet<Roles> roles;
	
	public UserAccount() {
		this.roles = EnumSet.of(Roles.USER);
		this.expirationDate = LocalDate.now().plusDays(60);
	}

	public UserAccount(String login, String password,  String firstName, String lastName) {
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
	
	public boolean changePassword(String newPassword) {
		if (!newPassword.equals(this.password)) {
			this.password = newPassword;
			this.expirationDate = LocalDate.now().plusDays(60);
			return true;
		}
		return false;
	}
	
	public boolean isPasswordExpire() {
		return LocalDate.now().isBefore(expirationDate);
	}
}
