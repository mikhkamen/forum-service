package telran.java47.user.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java47.user.model.User;


public interface UserRepository extends MongoRepository<User, String> {

	boolean existsByLogin(String login);

	User findByLogin(String login);
}
