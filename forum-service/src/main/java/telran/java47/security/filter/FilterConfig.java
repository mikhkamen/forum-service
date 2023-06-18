package telran.java47.security.filter;

import java.util.Set;


import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;

public class FilterConfig {
	private static final Set<String> publicEndpoints = new HashSet<>();

	public FilterConfig() {

		publicEndpoints.add("POST/forum/posts/tags");
		publicEndpoints.add("^GET/forum/posts/author/(/[\\s\\p{Cntrl}]*)?$");
		publicEndpoints.add("POST/forum/posts/period");
		publicEndpoints.add("POST/account/register");
		publicEndpoints.add("POST/account/login");
	}

	public static boolean ifEndPointPublic(HttpServletRequest request) {
		String joinedRequest =  request.getMethod() + request.getServletPath() ;
		for (String endpoint : publicEndpoints) {
	        if (joinedRequest.startsWith(endpoint)) {
	            return true;
	        }
		}
		return false;
	}
}
