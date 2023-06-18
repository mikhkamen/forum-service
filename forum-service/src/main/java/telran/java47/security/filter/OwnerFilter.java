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
import telran.java47.post.dao.PostRepository;
import telran.java47.post.model.Post;

@Component
@RequiredArgsConstructor
@Order(20)
public class OwnerFilter implements Filter {

	final UserAccountRepository userAccountRepository;
	final PostRepository postRepository;
	

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (request.getUserPrincipal() != null) {
			String name = request.getUserPrincipal().getName();
			UserAccount user = userAccountRepository.findById(name).orElse(null);
			String path = request.getRequestURI();

			if (path.split("/")[1].equals("account")) {
				if (user == null || (!checkIfOwner(name, path.split("/")[3])) &&(!checkRole("ADMIN", user.getRoles()))
						&& checkEndPoint(request.getMethod(), request.getServletPath())) {
					response.sendError(403, "role mismatch");
					return;
				}
			} 
			else if (path.split("/")[1].equals("forum")) {
				System.out.println(path.split("/")[3]);
				Post post = postRepository.findById(path.split("/")[3]).orElse(null);

				if (post != null) {
					if (!(checkIfAuthor(post, request))
							&& checkEndPoint(request.getMethod(), request.getServletPath()) || (!(checkIfAllowedDeletePost(post, request, path)))) {
						response.sendError(403, "role mismatch or you are not the author");
						return;
					}
				}
			}
			
		}
		chain.doFilter(request, response);
		
	}
	

	private boolean checkIfOwner(String name, String login) {
		return name.equals(login);
	}

	private boolean checkEndPoint(String method, String path) {
		return (path.matches("^/forum/post/([^\\s]+?)") || path.matches("^/account/user/([^\\s]+?)$"))
				&& ("PUT".equalsIgnoreCase(method));
	}
	
	private boolean checkRole(String role, Set<String> roles) {
		return roles.contains(role);
	}

	private boolean checkIfAuthor(Post post, HttpServletRequest request) {
		return post.getAuthor().equals(request.getUserPrincipal().getName());
	}

	private boolean checkIfAllowedDeletePost(Post post, HttpServletRequest request, String path) {
		String name = request.getUserPrincipal().getName();
		UserAccount user = userAccountRepository.findById(name).orElse(null);
		return (checkIfAuthor(post, request) || user.getRoles().contains("MODERATOR"))
				&& path.matches("^/forum/post/([^\\s]+?)$") && "DELETE".equals(request.getMethod());
	}
}