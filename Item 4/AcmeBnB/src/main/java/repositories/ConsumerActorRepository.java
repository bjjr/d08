
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ConsumerActor;

@Repository
public interface ConsumerActorRepository extends JpaRepository<ConsumerActor, Integer> {

	@Query("select ca from ConsumerActor ca where ca.userAccount.id = ?1")
	ConsumerActor findByUserAccountId(int userAccountId);

}
