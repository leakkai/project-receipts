package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select u from User u where u.id = :id")
	User findUser(@Param("id") int id);
	
	@Query("select u from User u where u.name = :name")
	User findUserByName(@Param("name") String name);
}
