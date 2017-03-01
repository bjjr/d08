
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	@Query("select p from Property p where p.lessor.id = ?1 order by p.audits.size desc")
	Collection<Property> getPropertiesSortedByAudits(int lessorId);
	
	@Query("select p from Property p where p.lessor.id = ?1 order by p.books.size desc")
	Collection<Property> getPropertiesSortedByBooks(int lessorId);
	
}
