
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

	@Query("select a.attribute from AttributeValue a group by a.attribute")
	Collection<Attribute> findListAttributesSortedByTimesUsed();
}
