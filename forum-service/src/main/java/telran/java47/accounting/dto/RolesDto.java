package telran.java47.accounting.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java47.accounting.model.Roles;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {
	String login;
    Set<Roles> roles;
}
