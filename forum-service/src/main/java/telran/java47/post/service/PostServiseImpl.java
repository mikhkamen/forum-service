package telran.java47.post.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import telran.java47.post.dao.PostRepository;
import telran.java47.post.dto.CommentCreateDto;
import telran.java47.post.dto.PeriodDto;
import telran.java47.post.dto.PostCreateDto;
import telran.java47.post.dto.PostDto;
import telran.java47.post.dto.PostUpdateDto;
import telran.java47.post.exceptions.PostNotFoundException;
import telran.java47.post.model.Post;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class PostServiseImpl implements PostService {

	final PostRepository postRepository;	
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String author, PostCreateDto postCreateDto) {
		if (author == null || postCreateDto.getContent() == null || postCreateDto.getTitle() == null
				|| postCreateDto.getTags() == null)
			return null;
		Post post = modelMapper.map(postCreateDto, Post.class);
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = postRepository.findById(id).orElse(null);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	@PutMapping("/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addLike(@PathVariable String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		return postRepository.findByAuthorIgnoreCase(author).stream()
				.map(post -> modelMapper.map( post, PostDto.class))
				.toList();
	}

	@Override
	public PostDto addComment(String id, String user, CommentCreateDto commentCreateDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		if(post.addComment(user, commentCreateDto.getMessage())) {
			postRepository.save(post);			
		};
		return modelMapper.map(post, PostDto.class);		
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return postRepository.findByTagsIgnoreCase(tags).stream()
				.map(post -> modelMapper.map( post, PostDto.class))
				.toList();		 
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto periodDto) {		
		return postRepository.findByDateBetween(periodDto.getDateFrom(), periodDto.getDateTo()).stream()
				.map(post -> modelMapper.map( post, PostDto.class))
				.toList();
	}

	@Override
	public PostDto updatePost(String id, PostUpdateDto postUpdateDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		post.updatePost(postUpdateDto.getTitle(), postUpdateDto.getTags(), postUpdateDto.getContent());
		postRepository.save(post);

		return modelMapper.map(post, PostDto.class);
	}
}
