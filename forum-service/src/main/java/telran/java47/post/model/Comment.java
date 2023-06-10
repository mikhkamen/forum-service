package telran.java47.post.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of= {"user", "dateCreated"})
public class Comment {
	private LocalDateTime dateCreated ;
	private int likes;
	@Setter
	private String message;
	@Setter
	private String user;
	
	public Comment() {
		this.dateCreated = LocalDateTime.now();
	}
	
	public Comment(String message, String user) {
		this();
		this.message = message;
		this.user = user;
	}
	
	public void addLike() {
		likes++;
	}

	
}
