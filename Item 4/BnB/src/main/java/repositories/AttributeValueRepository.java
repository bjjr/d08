
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ConsumerActor;

@Repository
public interface AttributeValueRepository extends JpaRepository<ConsumerActor, Integer> {

}
