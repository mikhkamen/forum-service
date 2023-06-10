package telran.java47.post.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java47.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByAuthorIgnoreCase(String author);

	@Query("{ 'tags': { $in: ?0 } }")
     List<Post> findByTagsIgnoreCase(List<String> tags);;

	 @Query("{ 'dateCreated': { $gte: ?0, $lte: ?1 } }")
	    List<Post> findByDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
