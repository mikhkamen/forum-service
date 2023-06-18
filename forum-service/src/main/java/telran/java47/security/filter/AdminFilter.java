package telran.java47.security.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java47.accounting.dao.UserAccountRepository;
import telran.java47.accounting.model.UserAccount;

@Component
@RequiredArgsConstructor
@Order(20)
public class AdminFilter implements Filter {

	final UserAccountRepository userAccountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (request.getUserPrincipal() != null) {
			String name = request.getUserPrincipal().getName();
			UserAccount user = userAccountRepository.findById(name).orElse(null);
			if (user == null || (!checkRole("ADMIN", user.getRoles()))
					&& checkEndPoint(request.getMethod(), request.getServletPath())) {
				response.sendError(403, "role mismatch");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean checkRole(String role, Set<String> roles) {
		return roles.contains(role);
	}

	private boolean checkEndPoint(String method, String path) {
		return path.matches("^/account/user/([^\\s]+?)/role/([^\\s]+?)$");
	}
}
