package telran.java47.post.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import telran.java47.post.dto.CommentCreateDto;


@Data
@Document(collection = "posts")
@EqualsAndHashCode(of = "id")
public class Post {
	private String id;
	@Setter
	private String title;                            
	@Setter
	private String content;
	@Setter
	private List<String> tags;
	@Setter
	private String author;
	private LocalDateTime dateCreated;
	private int likes;
	private List<Comment> comments;

	ModelMapper modelMapper;
	
	public Post() {
		this.dateCreated = LocalDateTime.now();
		this.comments = new ArrayList<Comment>();
	}

	public Post(String title, String content, List<String> tags, String author, LocalDateTime dateCreated) {
		this();
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.author = author;		
	}

	public boolean addComment(String user, CommentCreateDto commentCreateDto) {
		if (commentCreateDto.getMessage() == null || user == null)
			return false;
		Comment comment = new Comment(commentCreateDto.getMessage(), user);
		return comments.add(comment);
	}

	public void addLike() {
		likes++;
	}

	public boolean addTag(String tag) {
		return tags.add(tag);
	}
	
	public boolean removeTag(String tag) {
		return tags.remove(tag);
	}
	
}
