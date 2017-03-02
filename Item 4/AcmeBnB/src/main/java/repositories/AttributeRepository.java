
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
	
	/* @Query("select a from Attribute a join a.attributeValues av join av.properties p group by a order by count(a) desc")
	Collection<Attribute> getAttributesOrderedByUse(); */

}
