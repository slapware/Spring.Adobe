package spring.adobe.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

@Repository
public interface SourceRepository extends CrudRepository<Source, Long> {
	@Query(value = "SELECT * FROM source s WHERE s.orderid = ?1", nativeQuery = true)
    public List<Source> findByOrder(@Param("searchTerm") String searchTerm);
}
