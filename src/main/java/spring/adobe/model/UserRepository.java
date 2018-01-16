package spring.adobe.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository  extends CrudRepository<AUser, Long>, UserRepositoryCustom  {
	@Query(value = "SELECT * FROM auser au WHERE au.username = ?1 AND au.password = md5(?2)", nativeQuery = true)
    public List<AUser> findByLogin(@Param("username") String username, @Param("password") String password);

	@Procedure
	  Integer adept_userEntry(String password, String username, String uuid);

}
