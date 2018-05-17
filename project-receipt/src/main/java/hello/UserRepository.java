package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select u from User u where u.id = :id")
	List<User> findById(@Param("id") int id);
	
	@Query("select u from User u where u.name = :name")
	List<User> findByName(@Param("name") String name);
	
	@Query("select u from User u where u.email = :email")
	List<User> findByEmail(@Param("email") String email);
}
