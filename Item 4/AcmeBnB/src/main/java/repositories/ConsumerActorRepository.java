
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ConsumerActor;

@Repository
public interface ConsumerActorRepository extends JpaRepository<ConsumerActor, Integer> {

}
