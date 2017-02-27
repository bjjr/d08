
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AttributeValue;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {

	@Query("select a from AttributeValue where a.property.id = ?1")
	Collection<AttributeValue> findAttributesValuesByProperty(int propertyId);
}
