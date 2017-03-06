
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer> {

	@Query("select min(a.socialIdentities.size) from Actor a")
	Double findMinSocialIdentities();

	@Query("select avg(a.socialIdentities.size) from Actor a")
	Double findAvgSocialIdentities();

	@Query("select max(a.socialIdentities.size) from Actor a")
	Double findMaxSocialIdentities();

}
