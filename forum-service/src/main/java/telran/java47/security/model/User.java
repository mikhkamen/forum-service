package telran.java47.security.model;

import java.security.Principal;
import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import telran.java47.enums.Roles;

@AllArgsConstructor
public class User implements Principal {
	String userName;
	@Getter
	EnumSet<Roles> roles;

	@Override
	public String getName() {
		return userName;
	}

}
