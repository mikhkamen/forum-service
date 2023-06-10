package telran.java47.authorization;

import org.springframework.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthorizationBasicHeaderParser {
	public static String[] parseBasicAuthHeader(HttpHeaders headers) {
        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.toLowerCase().startsWith("basic ")) {
            String base64Credentials = authHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            return credentials.split(":", 2);
        }
        return null;
    }
}
