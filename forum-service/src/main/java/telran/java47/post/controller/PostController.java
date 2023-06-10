package telran.java47.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.post.dto.CommentCreateDto;
import telran.java47.post.dto.PeriodDto;
import telran.java47.post.dto.PostCreateDto;
import telran.java47.post.dto.PostDto;
import telran.java47.post.dto.PostUpdateDto;
import telran.java47.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
	
	final PostService postService;

	@PostMapping("/post/{author}")
	public PostDto addPost(@PathVariable String author, @RequestBody PostCreateDto postCreateDto) {
		return postService.addPost(author, postCreateDto);
	}

	@GetMapping("/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return postService.findPostById(id);
	}
	
	@PutMapping("/post/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public  void addLike(@PathVariable String id) {
		postService.addLike(id);
	}
	
	@GetMapping("/posts/author/{author}")
	public List<PostDto> findPostsByAuthor(@PathVariable String author) {
		return postService.findPostsByAuthor(author);
	}
	
	@PutMapping("/post/{id}/comment/{user}")
	public PostDto addComment(@PathVariable String id, @PathVariable String user, @RequestBody CommentCreateDto commentCreateDto) {
		return postService.addComment(id, user, commentCreateDto);
	}
	
	@DeleteMapping("/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return postService.deletePost(id);
	}
	
	@PostMapping("/posts/tags")
	public List<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return postService.findPostsByTags(tags);
	}
	
	@PostMapping("/posts/period")
	public List<PostDto> findPostsByPeriod(@RequestBody PeriodDto periodDto) {
		return postService.findPostsByPeriod(periodDto);
	}
	
	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody PostUpdateDto postUpdateDto) {
		return postService.updatePost(id, postUpdateDto);
	}
}                                                                                                          
