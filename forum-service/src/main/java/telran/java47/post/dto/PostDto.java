package telran.java47.post.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import telran.java47.post.model.Comment;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
	String id;
	String title;
	String content;
	@Singular
	List<String> tags;
	String author;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dateCreated;
	Long likes;
	@Singular
	List<Comment> comments;
	
//	public PostDto(String id, String title, String content, List<String> tags, String author, LocalDateTime dateCreated,
//			long likes) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.tags = tags;
//		this.author = author;
//		this.dateCreated = dateCreated;
//		this.likes = likes;
//		this.comments = comments;
//	}
	
	
	
}
